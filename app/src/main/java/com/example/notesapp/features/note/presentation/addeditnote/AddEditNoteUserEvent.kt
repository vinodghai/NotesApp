package com.example.notesapp.features.note.presentation.addeditnote

import androidx.compose.ui.focus.FocusState

sealed class AddEditNoteUserEvent {

    data class EnteredTitle(val title: String) : AddEditNoteUserEvent()
    data class ChangeTitleFocus(val focusState: FocusState) : AddEditNoteUserEvent()
    data class EnteredContent(val content: String) : AddEditNoteUserEvent()
    data class ChangeContentFocus(val focusState: FocusState) : AddEditNoteUserEvent()
    data class ChangeColor(val color: Int) : AddEditNoteUserEvent()
    object SaveNote : AddEditNoteUserEvent()
}