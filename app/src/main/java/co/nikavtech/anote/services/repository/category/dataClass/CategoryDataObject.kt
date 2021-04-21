package co.nikavtech.anote.services.repository.category.dataClass

import co.nikavtech.anote.database.entities.NoteEntity

data class CategoryDataObject(
    private var _id:Int,
    private var _title:String,
    private var _notes:List<NoteEntity>?
){
    val id = _id
    val title = _title
    val notes = _notes
}
