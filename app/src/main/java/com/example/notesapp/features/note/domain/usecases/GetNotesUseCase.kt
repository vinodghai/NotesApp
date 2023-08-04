package com.example.notesapp.features.note.domain.usecases

import com.example.notesapp.features.note.domain.models.Note
import com.example.notesapp.features.note.domain.repository.NoteRepository
import com.example.notesapp.features.note.domain.util.NoteOrder
import com.example.notesapp.features.note.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNotesUseCase(private val repository: NoteRepository) {

    operator fun invoke(
        noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending)
    ): Flow<List<Note>> = repository.getNotes().map { notes ->
        when (noteOrder.orderType) {
            is OrderType.Ascending -> when (noteOrder) {
                is NoteOrder.Title -> notes.sortedBy { it.title.lowercase() }
                is NoteOrder.Date -> notes.sortedBy { it.timestamp }
                is NoteOrder.Color -> notes.sortedBy { it.color }
            }

            is OrderType.Descending -> when (noteOrder) {
                is NoteOrder.Title -> notes.sortedByDescending { it.title.lowercase() }
                is NoteOrder.Date -> notes.sortedByDescending { it.timestamp }
                is NoteOrder.Color -> notes.sortedByDescending { it.color }
            }
        }
    }
}