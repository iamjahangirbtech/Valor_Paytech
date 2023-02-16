package com.valor_paytech.task.ui.user_detail

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.valor_paytech.task.repository.api.ApiServices
import com.valor_paytech.task.repository.db.cart.UserDao
import com.valor_paytech.task.repository.model.UserDetailModel
import com.valor_paytech.task.repository.model.Users
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserDetailRepository @Inject constructor(
    private val cartDao: UserDao,
    private val apiServices: ApiServices,
    @ApplicationContext val context: Context
) {

    val userResponse = MutableLiveData<Users>()
    fun getUserDetailApiCall(userId:Int): MutableLiveData<Users> {
        val call =  apiServices.getUserDetail(userId)
        call.enqueue(object: Callback<Users> {
            override fun onFailure(call: Call<Users>, t: Throwable) {
                Log.v("DEBUG : ", t.message.toString())
            }
            override fun onResponse(
                call: Call<Users>,
                response: Response<Users>
            ) {
                Log.v("DEBUG : ", response.body().toString())
                val data = response.body()
                userResponse.value = data!!
            }
        })

        return userResponse
    }

  /*  suspend fun getCartItemCall(): List<Cart> {
        var cartLiveData: List<Cart> = ArrayList()
        withContext(Dispatchers.IO) {
            cartLiveData =  cartDao.getAllCartItems()
        }
        return cartLiveData
    }

    suspend fun add(cart: Cart){
        withContext(Dispatchers.IO) {
            cartDao.insert(cart)
        }
    }

    suspend fun remove(productId: String) {
        withContext(Dispatchers.IO) {
            cartDao.deleteByProductId(productId)
        }
    }
    suspend fun updateByProductId(qty: Int,productId: String) {
        withContext(Dispatchers.IO) {
            cartDao.updateByProductId(qty,productId)
        }
    }*/

}