package co.nikavtech.anote.screens.fragments.home.listAdapter

import co.nikavtech.anote.database.entities.NoteDataObject

interface NoteItemEvent {
    fun onDeleteClicked(noteDataObject: NoteDataObject)
    fun onViewClicked(noteDataObject: NoteDataObject)
}