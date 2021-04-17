package co.nikavtech.anote.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import co.nikavtech.anote.database.dao.NoteDao
import co.nikavtech.anote.database.dao.UserDao

abstract class NoteDatabase : RoomDatabase() {
    abstract val noteDao: NoteDao
    abstract val userDao: UserDao

    companion object {
        @Volatile
        var INSTANCE: NoteDatabase? = null

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