package com.redeyesncode.androidtechnical.room

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "posts_table")
data class PostTable(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val userId: Int,
    val apiId: Int,
    val body: String,
    val title: String
)