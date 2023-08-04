package com.example.notesapp.features.note.presentation.addeditnote

sealed class AddEditNoteUiEvent {
    data class ShowSnackBar(val message: String): AddEditNoteUiEvent()
    object SaveNote: AddEditNoteUiEvent()
}
