package com.valor_paytech.task.ui.user_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.valor_paytech.task.repository.model.UserDetailModel
import com.valor_paytech.task.repository.model.Users
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class UserDetailViewModel @Inject constructor(private val userDetailRepository: UserDetailRepository) : ViewModel() {

    var servicesLiveData: MutableLiveData<Users>? = null
    fun getUserDetails(userId:Int) : LiveData<Users>? {
        servicesLiveData = userDetailRepository.getUserDetailApiCall(userId)
        return servicesLiveData
    }

    /*var cartLiveData: MutableLiveData<List<Cart>> = MutableLiveData()
    fun getCartList(): LiveData<List<Cart>> {
        viewModelScope.launch {
            val result = userDetailRepository.getCartItemCall()
            cartLiveData.postValue(result)
        }
        return cartLiveData
    }

    fun addToCart(cart: Cart) {
        viewModelScope.launch {
            userDetailRepository.add(cart)
        }
    }

    fun updateToCart(qty: Int,productId: String) {
        viewModelScope.launch {
            userDetailRepository.updateByProductId(qty,productId)
        }
    }

    fun removeFromCart(productId: String) {
        viewModelScope.launch {
            userDetailRepository.remove(productId)
        }
    }*/

}