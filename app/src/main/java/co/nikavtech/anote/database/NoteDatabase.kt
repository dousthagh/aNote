package co.nikavtech.anote.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import co.nikavtech.anote.database.dao.NoteDao
import co.nikavtech.anote.database.dao.UserDao
import co.nikavtech.anote.database.entities.NoteDataObject
import co.nikavtech.anote.database.entities.UserModel

@Database(entities = [UserModel::class, NoteDataObject::class], exportSchema = false, version = 1)
abstract class NoteDatabase : RoomDatabase() {
    abstract val noteDao: NoteDao
    abstract val userDao: UserDao

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