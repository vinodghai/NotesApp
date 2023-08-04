package com.example.notesapp.features.note.domain.usecases

import com.example.notesapp.features.note.domain.models.Note
import com.example.notesapp.features.note.domain.repository.NoteRepository

class GetNoteUseCase(
    private val repository: NoteRepository
) {

    suspend operator fun invoke(id: Int): Note? = repository.getNoteById(id)
}