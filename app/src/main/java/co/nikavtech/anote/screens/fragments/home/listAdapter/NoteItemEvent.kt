package co.nikavtech.anote.screens.fragments.home.listAdapter

import co.nikavtech.anote.database.entities.NoteEntity

interface NoteItemEvent {
    fun onDeleteClicked(noteEntity: NoteEntity)
    fun onViewClicked(noteEntity: NoteEntity)
}