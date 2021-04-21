package co.nikavtech.anote.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import co.nikavtech.anote.database.dao.CategoryDao
import co.nikavtech.anote.database.dao.NoteDao
import co.nikavtech.anote.database.dao.UserDao
import co.nikavtech.anote.database.entities.CategoryEntity
import co.nikavtech.anote.database.entities.NoteEntity
import co.nikavtech.anote.database.entities.UserEntity

@Database(
    entities = [UserEntity::class, NoteEntity::class, CategoryEntity::class],
    exportSchema = false,
    version = 2
)
abstract class NoteDatabase : RoomDatabase() {
    abstract val noteDao: NoteDao
    abstract val userDao: UserDao
    abstract val categoryDao: CategoryDao

    companion object {
        @Volatile
        private var INSTANCE: NoteDatabase? = null

        fun getInstance(context: Context): NoteDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        NoteDatabase::class.java,
                        "db_note"
                    ).build()
                }
                return instance
            }
        }
    }

}