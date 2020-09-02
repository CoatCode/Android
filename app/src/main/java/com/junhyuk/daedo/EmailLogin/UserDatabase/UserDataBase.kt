package com.junhyuk.daedo.EmailLogin.UserDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.util.concurrent.Executors

@Database(entities = [User::class], version = 1)
abstract class UserDataBase : RoomDatabase() {
    abstract fun wordDao(): UserDao?

    companion object {
        @Volatile
        private var INSTANCE: UserDataBase? = null
        private const val NUMBER_OF_THREADS = 4
        val databaseWriteExecutor = Executors.newFixedThreadPool(
            NUMBER_OF_THREADS
        )

        fun getDatabase(context: Context): UserDataBase? {
            if (INSTANCE == null) {
                synchronized(UserDataBase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            UserDataBase::class.java, "word_database"
                        )
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }
}