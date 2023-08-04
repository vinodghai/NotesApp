package com.example.notesapp.features.note.presentation.notes

import com.example.notesapp.features.note.domain.models.Note
import com.example.notesapp.features.note.domain.util.NoteOrder
import com.example.notesapp.features.note.domain.util.OrderType

data class NotesUiState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)