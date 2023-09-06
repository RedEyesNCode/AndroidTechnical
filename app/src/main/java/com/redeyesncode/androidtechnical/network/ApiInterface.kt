package com.redeyesncode.androidtechnical.network

import com.redeyesncode.androidtechnical.data.PostCommentResponse
import com.redeyesncode.androidtechnical.data.PostsResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiInterface {

    @GET("posts")
    suspend fun getAllPosts(): Response<ArrayList<PostsResponse>>

    @GET("posts/{post_id}/comments")
    suspend fun getPostComment(
        @Path("post_id") post_id: String,
    ): Response<ArrayList<PostCommentResponse>>
}