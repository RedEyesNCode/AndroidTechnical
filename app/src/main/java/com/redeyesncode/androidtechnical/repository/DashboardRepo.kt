package com.redeyesncode.androidtechnical.repository

import com.redeyesncode.androidtechnical.base.Resource
import com.redeyesncode.androidtechnical.data.PostCommentResponse
import com.redeyesncode.androidtechnical.data.PostsResponse

interface DashboardRepo {

    suspend fun getAllPosts(): Resource<ArrayList<PostsResponse>>
    suspend fun getPostComment(post_id:String): Resource<ArrayList<PostCommentResponse>>


}