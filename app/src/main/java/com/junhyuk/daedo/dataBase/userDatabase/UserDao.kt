package com.junhyuk.daedo.dataBase.userDatabase

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

    @Query("UPDATE UserInformation set id = :id ,email = :email, Username = :Username, profile = :profile, description = :description, followers = :followers, following = :following WHERE idx = :idx")
    fun update(idx : Int,id : String, email : String, Username : String, profile : String,description : String, followers : String, following : String)

    @Query("DELETE FROM UserInformation WHERE idx = :idx") fun delete(idx : Int)

}