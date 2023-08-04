package com.example.notesapp.features.note.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.notesapp.features.note.presentation.addeditnote.AddEditNoteScreen
import com.example.notesapp.features.note.presentation.addeditnote.PARAM_NOTE_COLOR
import com.example.notesapp.features.note.presentation.addeditnote.PARAM_NOTE_ID
import com.example.notesapp.features.note.presentation.notes.NotesScreen
import com.example.notesapp.features.note.presentation.util.Screen
import com.example.notesapp.ui.theme.NotesAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.NotesScreen.route
                    ) {
                        composable(Screen.NotesScreen.route) {
                            NotesScreen(navController = navController)
                        }
                        composable(
                            route = Screen.AddEditNoteScreen.route +
                                    "?$PARAM_NOTE_ID={$PARAM_NOTE_ID}&$PARAM_NOTE_COLOR={$PARAM_NOTE_COLOR}",
                            arguments = listOf(
                                navArgument(
                                    name = PARAM_NOTE_ID
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                                navArgument(
                                    name = PARAM_NOTE_COLOR
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                }
                            )
                        ) {
                            val color = it.arguments?.getInt(PARAM_NOTE_COLOR) ?: -1
                            AddEditNoteScreen(navController = navController, noteColor = color)
                        }
                    }
                }
            }
        }
    }
}
