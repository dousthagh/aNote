package co.nikavtech.anote.services.repository.category

import co.nikavtech.anote.services.repository.category.dataClass.CategoryDataObject
import co.nikavtech.anote.database.entities.NoteEntity

class LoadCategoryService {
    fun getCategory(id: Int): CategoryDataObject {
        return CategoryDataObject(
            2, _title = "category_2", _notes = listOf(
                NoteEntity(
                    _id = 1,
                    _title = "note_1",
                    _text = "hello my dear user, this is sample note",
//                    _insertedDate = "2021-04-08",
                )
            )
        )
    }

    fun loadAllCategory(): List<CategoryDataObject> {
        return listOf(
            CategoryDataObject(1, _title = "category_1", _notes = null),
            CategoryDataObject(2, _title = "category_2", _notes = null)
        )
    }
}
