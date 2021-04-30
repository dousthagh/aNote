package co.nikavtech.anote.database.entities

import androidx.room.Embedded
import androidx.room.Relation
import java.io.Serializable

data class NoteWithCategoryEntity(
    @Embedded val noteEntity: NoteEntity,
    @Relation(
        parentColumn =  "category_id",
        entityColumn = "id"
    )
    val categoryEntity: CategoryEntity?
):Serializable
