package com.junhyuk.daedo.EmailLogin.UserDatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
//아직 미완성 Dao
@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(User: User?)

    @Query("DELETE FROM UserInformation")
    fun deleteAll()

    @Query("SELECT * from UserInformation ORDER BY word ASC")
    open fun getAlphabetizedWords(): LiveData<MutableList<User?>?>?
}