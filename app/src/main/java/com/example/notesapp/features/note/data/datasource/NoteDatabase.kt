package com.example.notesapp.features.note.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.notesapp.features.note.domain.models.Note

@Database(
    entities = [Note::class],
    version = 1
)
abstract class NoteDatabase: RoomDatabase() {

    abstract val noteDao: NoteDao

    companion object {
        const val DATABASE_NAME = "notes_db"
    }
}