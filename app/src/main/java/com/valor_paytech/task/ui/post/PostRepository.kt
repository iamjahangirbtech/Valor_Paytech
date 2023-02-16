package com.valor_paytech.task.ui.post

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.valor_paytech.task.repository.api.ApiServices
import com.valor_paytech.task.repository.db.User
import com.valor_paytech.task.repository.db.cart.UserDao
import com.valor_paytech.task.repository.model.Comments
import com.valor_paytech.task.repository.model.Posts
import com.valor_paytech.task.repository.model.PostsDbModel
import com.valor_paytech.task.repository.model.PostsModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.HashMap
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostRepository @Inject constructor(
    private val cartDao: UserDao,
    private val apiServices: ApiServices,
    @ApplicationContext val context: Context
) {

    val productResponse = MutableLiveData<MutableList<Posts>>()
    fun getPostsApiCall(userId : Int): MutableLiveData<MutableList<Posts>> {

        val call =  apiServices.getPost(userId)
        call.enqueue(object: Callback<MutableList<Posts>> {
            override fun onFailure(call: Call<MutableList<Posts>>, t: Throwable) {
                Log.v("DEBUG : ", t.message.toString())
            }
            override fun onResponse(
                call: Call<MutableList<Posts>>,
                response: Response<MutableList<Posts>>
            ) {
                Log.v("DEBUG : ", response.body().toString())
                val data = response.body()
                productResponse.value = data!!
            }
        })

        return productResponse
    }

    val commentResponse = MutableLiveData<MutableList<Comments>>()
    fun getCommentsApiCall(postId : Int): MutableLiveData<MutableList<Comments>> {
        val call =  apiServices.getComments(postId)
        call.enqueue(object: Callback<MutableList<Comments>> {
            override fun onFailure(call: Call<MutableList<Comments>>, t: Throwable) {
                Log.v("DEBUG : ", t.message.toString())
            }
            override fun onResponse(
                call: Call<MutableList<Comments>>,
                response: Response<MutableList<Comments>>
            ) {
                Log.v("DEBUG : ", response.body().toString())
                val data = response.body()
                commentResponse.value = data!!
            }
        })

        return commentResponse
    }

    suspend fun getAllUserItemCall(): List<User> {
        var userLiveData: List<User> = ArrayList()
        withContext(Dispatchers.IO) {
            userLiveData =  cartDao.getAllUserItems()
        }
        return userLiveData
    }

    suspend fun addAllUser(Posts: List<User>){
        withContext(Dispatchers.IO) {
            cartDao.insertAll(Posts)
        }
    }
    suspend fun addPost(postsDbModel: PostsDbModel){
        withContext(Dispatchers.IO) {
            cartDao.insertPost(postsDbModel)
        }
    }
    suspend fun getPost(postId : Int) : PostsDbModel{
        var postsDbModel:PostsDbModel
        withContext(Dispatchers.IO) {
            postsDbModel = cartDao.getPostData(postId)
        }
        return postsDbModel
    }


}