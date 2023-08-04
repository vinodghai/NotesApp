package com.example.notesapp.features.note.presentation.notes

import com.example.notesapp.features.note.domain.models.Note
import com.example.notesapp.features.note.domain.util.NoteOrder

sealed class NotesUserEvent {
    data class Order(val noteOrder: NoteOrder): NotesUserEvent()
    data class DeleteNote(val note: Note): NotesUserEvent()
    object RestoreNote: NotesUserEvent()
    object Toggle: NotesUserEvent()
}
