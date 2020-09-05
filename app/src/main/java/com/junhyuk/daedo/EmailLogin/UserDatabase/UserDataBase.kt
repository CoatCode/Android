package com.junhyuk.daedo.EmailLogin.UserDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 2,exportSchema = false)
abstract class UserDataBase : RoomDatabase() {
    abstract fun USerDao(): UserDao?

    companion object {
        @Volatile
        private var INSTANCE: UserDataBase? = null

        fun getDatabase(context: Context): UserDataBase? {
            if (INSTANCE == null) {
                synchronized(UserDataBase::class.java) {

                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            UserDataBase::class.java, "word_database"
                        )
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }
}