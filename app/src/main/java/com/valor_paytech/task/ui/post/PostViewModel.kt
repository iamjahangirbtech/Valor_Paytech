package com.valor_paytech.task.ui.post

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valor_paytech.task.repository.db.User
import com.valor_paytech.task.repository.model.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(private val postRepository: PostRepository) : ViewModel() {

    var servicesLiveData: MutableLiveData<MutableList<Posts>>? = null
    var commentsLiveData: MutableLiveData<MutableList<Comments>>? = null

    fun getPosts(userId : Int) : LiveData<MutableList<Posts>>? {
        servicesLiveData = postRepository.getPostsApiCall(userId)
        return servicesLiveData
    }
    fun getPostsComments(postId : Int) : LiveData<MutableList<Comments>>? {
        commentsLiveData = postRepository.getCommentsApiCall(postId)
        return commentsLiveData
    }

 /*   var cartLiveData: MutableLiveData<List<User>> = MutableLiveData()
    fun getCartList(): LiveData<List<User>> {
        viewModelScope.launch {
            val result = valpostRepository.getAllUserItemCall()
            cartLiveData.postValue(result)
        }
        return cartLiveData
    }*/

    fun addAllUser(user: List<User>) {
        viewModelScope.launch {
            postRepository.addAllUser(user)
        }
    }

    fun addPost(postsDbModel: PostsDbModel) {
        viewModelScope.launch {
            postRepository.addPost(postsDbModel)
        }
    }
/*    fun getPostDetails(postId : Int) : PostsDbModel{
        var postsDbModel:PostsDbModel? = null
        viewModelScope.launch {
            postsDbModel = postRepository.getPost(postId)
        }
        return postsDbModel!!

    }*/



}