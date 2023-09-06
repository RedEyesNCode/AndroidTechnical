package com.redeyesncode.androidtechnical.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PostDao {
    @Insert
    fun insertPost(comment: PostTable)

    @Query("SELECT * FROM posts_table")
    fun getAllComments(): List<PostTable>
}