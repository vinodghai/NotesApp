package com.example.notesapp.features.note.presentation.addeditnote

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.features.note.domain.models.InvalidNoteException
import com.example.notesapp.features.note.domain.models.Note
import com.example.notesapp.features.note.domain.usecases.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {


    private val _noteTitleState = mutableStateOf(
        NoteTextFieldState(
            hint = "Enter title..."
        )
    )
    val noteTitleState: State<NoteTextFieldState> = _noteTitleState

    private val _noteContentState = mutableStateOf(
        NoteTextFieldState(
            hint = "Enter content..."
        )
    )
    val noteContentState: State<NoteTextFieldState> = _noteContentState

    private val _noteColorState = mutableStateOf(Note.noteColors.random().toArgb())
    val noteColorState: State<Int> = _noteColorState

    private val _uiEventFlow = MutableSharedFlow<AddEditNoteUiEvent>()
    val uiEventFlow: SharedFlow<AddEditNoteUiEvent> = _uiEventFlow.asSharedFlow()

    private var currentNoteId: Int? = null

    init {
        savedStateHandle.get<Int>(PARAM_NOTE_ID)?.let { noteId ->
            if (noteId != -1) {
                viewModelScope.launch {
                    noteUseCases.getNoteUseCase(noteId)?.run {
                        currentNoteId = id
                        _noteTitleState.value = noteTitleState.value.copy(
                            text = title,
                            isHintVisible = false
                        )
                        _noteContentState.value = noteContentState.value.copy(
                            text = content,
                            isHintVisible = false
                        )
                        _noteColorState.value = color
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditNoteUserEvent) {
        when (event) {
            is AddEditNoteUserEvent.EnteredTitle -> {
                _noteTitleState.value = noteTitleState.value.copy(
                    text = event.title
                )
            }

            is AddEditNoteUserEvent.ChangeTitleFocus -> {
                _noteTitleState.value = noteTitleState.value.copy(
                    isHintVisible = !event.focusState.isFocused
                            && noteTitleState.value.text.isEmpty()
                )
            }

            is AddEditNoteUserEvent.EnteredContent -> {
                _noteContentState.value = noteContentState.value.copy(
                    text = event.content
                )
            }

            is AddEditNoteUserEvent.ChangeContentFocus -> {
                _noteContentState.value = noteContentState.value.copy(
                    isHintVisible = !event.focusState.isFocused
                            && noteContentState.value.text.isEmpty()
                )
            }

            is AddEditNoteUserEvent.ChangeColor -> {
                _noteColorState.value = event.color
            }

            is AddEditNoteUserEvent.SaveNote -> {
                viewModelScope.launch {
                    try {
                        noteUseCases.addNoteUseCase(
                            Note(
                                title = noteTitleState.value.text,
                                content = noteContentState.value.text,
                                timestamp = System.currentTimeMillis(),
                                color = noteColorState.value,
                                id = currentNoteId
                            )
                        )
                        _uiEventFlow.emit(AddEditNoteUiEvent.SaveNote)
                    } catch (e: InvalidNoteException) {
                        _uiEventFlow.emit(
                            AddEditNoteUiEvent.ShowSnackBar(
                                message = e.message ?: "Couldn't save note"
                            )
                        )
                    }
                }
            }
        }
    }
}