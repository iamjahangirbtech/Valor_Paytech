package com.valor_paytech.task.repository.api

import com.valor_paytech.task.repository.model.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

/**
 * Api services to communicate with server
 */

interface ApiServices {

    @GET("users")
    fun getUserList() : Call<MutableList<Users>>

    @GET("users/{userId}")
    fun getUserDetail(@Path("userId") userId: Int) : Call<Users>

    @GET("users/{userId}/posts")
    fun getPost(@Path("userId") userId: Int) : Call<MutableList<Posts>>

    @GET("posts/{postId}/comments")
    fun getComments(@Path("postId") postId: Int) : Call<MutableList<Comments>>

}
