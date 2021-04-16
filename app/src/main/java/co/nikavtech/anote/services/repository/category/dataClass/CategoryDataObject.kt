package co.nikavtech.anote.services.repository.category.dataClass

import co.nikavtech.anote.models.NoteDataObject

data class CategoryDataObject(
    private var _id:Int,
    private var _title:String,
    private var _notes:List<NoteDataObject>?
){
    val id = _id
    val title = _title
    val notes = _notes
}
