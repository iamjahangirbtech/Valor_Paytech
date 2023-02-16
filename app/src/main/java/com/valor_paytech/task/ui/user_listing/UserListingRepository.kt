package com.valor_paytech.task.ui.user_listing

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.valor_paytech.task.repository.api.ApiServices
import com.valor_paytech.task.repository.db.User
import com.valor_paytech.task.repository.db.cart.UserDao
import com.valor_paytech.task.repository.model.Users
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserListingRepository @Inject constructor(
    private val userDao: UserDao,
    private val apiServices: ApiServices,
    @ApplicationContext val context: Context
) {

    val productResponse = MutableLiveData<MutableList<Users>>()
    fun getUserListApiCall(): MutableLiveData<MutableList<Users>> {
        val call =  apiServices.getUserList()
        call.enqueue(object: Callback<MutableList<Users>> {
            override fun onFailure(call: Call<MutableList<Users>>, t: Throwable) {
                Log.v("DEBUG : ", t.message.toString())
            }
            override fun onResponse(
                call: Call<MutableList<Users>>,
                response: Response<MutableList<Users>>
            ) {
                Log.v("DEBUG : ", response.body().toString())
                val data = response.body()
                productResponse.value = data!!
            }
        })

        return productResponse
    }

    suspend fun getAllUserItemCall(): List<User> {
        var userLiveData: List<User> = ArrayList()
        withContext(Dispatchers.IO) {
            userLiveData =  userDao.getAllUserItems()
        }
        return userLiveData
    }

    suspend fun addAllUser(Users: List<User>){
        withContext(Dispatchers.IO) {
            userDao.insertAll(Users)
        }
    }
    suspend fun addUser(userModel: User){
        withContext(Dispatchers.IO) {
            userDao.insert(userModel)
        }
    }


}