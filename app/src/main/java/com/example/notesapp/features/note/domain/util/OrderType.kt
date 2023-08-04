package com.example.notesapp.features.note.domain.util

sealed class OrderType {
    object Ascending : OrderType()
    object Descending : OrderType()
}
