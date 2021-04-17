package co.nikavtech.anote.services.repository.note

import co.nikavtech.anote.database.entities.NoteDataObject

class LoadNoteService {
    fun loadAllNotes():List<NoteDataObject>{
        return listOf(
            NoteDataObject(
                _id = 1, _title = "note_1", _text = "hello dear user, this is sample note",
//                _insertedDate = "2021-04-08",
            ),
            NoteDataObject(
                _id = 1,
                _title = "note_2",
                _text = "hi, this is second sample note",
//                _insertedDate = "2021-04-09",
            )
        )
    }
}