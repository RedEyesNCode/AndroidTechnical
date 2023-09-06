package com.redeyesncode.androidtechnical.repository

import com.redeyesncode.androidtechnical.base.Resource
import com.redeyesncode.androidtechnical.base.safeCall
import com.redeyesncode.androidtechnical.data.PostCommentResponse
import com.redeyesncode.androidtechnical.data.PostsResponse
import com.redeyesncode.androidtechnical.network.RetrofitInstance

class DefaultDashboardRepo:DashboardRepo {
    override suspend fun getAllPosts(): Resource<ArrayList<PostsResponse>> {
        return safeCall {
            safeCall {
                val response =
                    RetrofitInstance().provideApiService(RetrofitInstance().provideRetrofit()).getAllPosts()
                Resource.Success(response.body()!!)
            }
        }
    }

    override suspend fun getPostComment(post_id: String): Resource<ArrayList<PostCommentResponse>> {
        return safeCall {
            safeCall {
                val response =
                    RetrofitInstance().provideApiService(RetrofitInstance().provideRetrofit()).getPostComment(post_id)
                Resource.Success(response.body()!!)
            }
        }
    }
}