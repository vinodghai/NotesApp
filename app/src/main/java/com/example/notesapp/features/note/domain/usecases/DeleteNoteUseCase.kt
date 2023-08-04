package com.example.notesapp.features.note.domain.usecases

import com.example.notesapp.features.note.domain.models.Note
import com.example.notesapp.features.note.domain.repository.NoteRepository

class DeleteNoteUseCase(private val repository: NoteRepository) {


    suspend operator fun invoke(note: Note) = repository.deleteNote(note)
}