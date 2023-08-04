package com.example.notesapp.features.note.domain.usecases

data class NoteUseCases(
    val getNotes: GetNotesUseCase,
    val deleteNoteUseCase: DeleteNoteUseCase,
    val addNoteUseCase: AddNoteUseCase,
    val getNoteUseCase: GetNoteUseCase
)
