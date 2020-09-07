package com.junhyuk.daedo.DataBase.UserDatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
//Dao
@Dao
interface UserDao {
    @Query("SELECT * FROM UserInformation")
    fun getAllUser() : List<UserTable>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(bookEntity : UserTable)

    @Query("UPDATE UserInformation set email = :email, Username = :Username, profile = :profile WHERE idx = :idx")
    fun update(idx : Int, email : String, Username : String, profile : String)

    @Query("DELETE FROM UserInformation WHERE idx = :idx") fun delete(idx : Int)

}