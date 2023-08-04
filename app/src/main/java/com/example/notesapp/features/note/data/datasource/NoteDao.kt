package com.example.notesapp.features.note.data.datasource

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.notesapp.features.note.domain.models.Note
import kotlinx.coroutines.flow.Flow


@Dao
interface NoteDao {

    @Query("SELECT * FROM Note")
    fun getNotes(): Flow<List<Note>>

    @Query("SELECT * FROM Note WHERE id = :id")
    suspend fun getNoteById(id: Int): Note?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)
}