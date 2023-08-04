package com.example.notesapp.di

import android.app.Application
import androidx.room.Room
import com.example.notesapp.features.note.data.datasource.NoteDatabase
import com.example.notesapp.features.note.data.repository.NoteRepositoryImpl
import com.example.notesapp.features.note.domain.repository.NoteRepository
import com.example.notesapp.features.note.domain.usecases.AddNoteUseCase
import com.example.notesapp.features.note.domain.usecases.DeleteNoteUseCase
import com.example.notesapp.features.note.domain.usecases.GetNoteUseCase
import com.example.notesapp.features.note.domain.usecases.GetNotesUseCase
import com.example.notesapp.features.note.domain.usecases.NoteUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesNoteDatabase(app: Application): NoteDatabase = Room.databaseBuilder(
        app,
        NoteDatabase::class.java,
        NoteDatabase.DATABASE_NAME
    ).build()

    @Provides
    @Singleton
    fun providesNoteRepository(db: NoteDatabase): NoteRepository = NoteRepositoryImpl(db.noteDao)

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository): NoteUseCases = NoteUseCases(
        getNotes = GetNotesUseCase(repository),
        deleteNoteUseCase = DeleteNoteUseCase(repository),
        addNoteUseCase = AddNoteUseCase(repository),
        getNoteUseCase = GetNoteUseCase(repository)
    )
}