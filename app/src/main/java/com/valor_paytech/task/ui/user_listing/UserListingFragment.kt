package com.valor_paytech.task.ui.user_listing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.google.gson.Gson
import com.valor_paytech.task.R
import com.valor_paytech.task.base.BaseFragment
import com.valor_paytech.task.databinding.FragmentUserListBinding
import com.valor_paytech.task.repository.db.User
import com.valor_paytech.task.repository.model.Data
import com.valor_paytech.task.repository.model.Users
import com.valor_paytech.task.ui.user_detail.UserDetailFragment
import com.valor_paytech.task.utils.extensions.gone
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class UserListingFragment : BaseFragment<FragmentUserListBinding>() {

    private val productListViewModel: UserListingViewModel by activityViewModels()
    private lateinit var userListingAdapter: UserListingAdapter
    private var usersModel : MutableList<Users> = ArrayList()

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentUserListBinding = FragmentUserListBinding.inflate(inflater, container, false)

    companion object {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        load()
        observeUserList()
    }


    private fun load() {
        binding?.emptyView?.emptyTitle?.text = "No Users Found"
        userListingAdapter = UserListingAdapter(usersModel)
        binding?.rvUsers?.adapter = userListingAdapter

        userListingAdapter.onDetailClicked = { user, pos ->
            var arguments: Bundle
            arguments = Bundle().apply { putInt("arg_1", user.id!!) }
            changeFragment(R.id.main_frame, UserDetailFragment(), arguments, true)
        }
    }


    private fun observeUserList() {
        productListViewModel.getUserList()!!.observe(activity!!, Observer { userListResponse ->
            //val newList: List<User> =  userListResponse.map { it.toString() }
            binding?.progressView?.root?.gone()
            println("userListViewModel = ${Gson().toJson(userListResponse)}")
            usersModel.clear()
            usersModel.addAll(userListResponse)
            userListingAdapter.notifyDataSetChanged()
        })
    }



}
