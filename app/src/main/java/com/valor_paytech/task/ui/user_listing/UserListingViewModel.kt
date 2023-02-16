package com.valor_paytech.task.ui.user_listing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valor_paytech.task.repository.db.User
import com.valor_paytech.task.repository.model.UserListModel
import com.valor_paytech.task.repository.model.Users
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UserListingViewModel @Inject constructor(private val userListingRepository: UserListingRepository) : ViewModel() {

    var servicesLiveData: MutableLiveData<MutableList<Users>>? = null

    fun getUserList() : LiveData<MutableList<Users>>? {
        servicesLiveData = userListingRepository.getUserListApiCall()
        return servicesLiveData
    }

    var cartLiveData: MutableLiveData<List<User>> = MutableLiveData()
    fun getCartList(): LiveData<List<User>> {
        viewModelScope.launch {
            val result = userListingRepository.getAllUserItemCall()
            cartLiveData.postValue(result)
        }
        return cartLiveData
    }

    fun addAllUser(user: List<User>) {
        viewModelScope.launch {
            userListingRepository.addAllUser(user)
        }
    }

    fun addUser(user: User) {
        viewModelScope.launch {
            userListingRepository.addUser(user)
        }
    }



}