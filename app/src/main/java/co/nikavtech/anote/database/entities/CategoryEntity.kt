package co.nikavtech.anote.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
class CategoryEntity(
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    var title:String
)