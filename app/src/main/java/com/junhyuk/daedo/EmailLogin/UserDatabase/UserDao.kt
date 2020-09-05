package com.junhyuk.daedo.EmailLogin.UserDatabase

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
    fun insert(bookEntity : User)



    @Query("UPDATE UserInformation set email = :email, Username = :Username, profile = :profile WHERE idx = :idx")
    fun update(idx : Int, email : String, Username : String, profile : String)

    @Query("DELETE FROM UserInformation WHERE idx = :idx") fun delete(idx : Int)

}