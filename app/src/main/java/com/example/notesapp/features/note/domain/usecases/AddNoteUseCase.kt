package com.example.notesapp.features.note.domain.usecases

import com.example.notesapp.features.note.domain.models.InvalidNoteException
import com.example.notesapp.features.note.domain.models.Note
import com.example.notesapp.features.note.domain.repository.NoteRepository

class AddNoteUseCase(
    private val repository: NoteRepository
) {

    suspend operator fun invoke(note: Note) {
        if (note.title.isBlank()) throw InvalidNoteException("Title must not be empty")
        if (note.content.isBlank()) throw InvalidNoteException("Content must not be empty")
        repository.insertNote(note = note)
    }
}