package co.nikavtech.anote.database.entities

import androidx.room.Embedded
import androidx.room.Relation

data class NoteWithCategoryEntity(
    @Embedded val noteEntity: NoteEntity,
    @Relation(
        parentColumn =  "category_id",
        entityColumn = "id"
    )
    val categoryEntity: CategoryEntity?
)
