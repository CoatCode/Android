package com.junhyuk.daedo.EmailLogin.UserDatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
//아직 미완성 Dao
@Dao
interface UserDao {
    @Query("SELECT * FROM UserInformation")
    fun getAllBook() : List<User>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(User: User?)

    @Query("UPDATE userinformation set email = :email, Username = :Username, profile = :profile, WHERE idx = :idx")
    fun update(idx : Int, name : String, author : String, kind : String, price : Int)

}