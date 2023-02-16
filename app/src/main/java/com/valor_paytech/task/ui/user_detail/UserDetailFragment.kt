package com.valor_paytech.task.ui.user_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.valor_paytech.task.base.BaseFragment
import com.valor_paytech.task.databinding.FragmentUserDetailBinding
import com.valor_paytech.task.utils.extensions.gone
import com.valor_paytech.task.utils.loadCenterInside
import com.google.gson.Gson
import com.valor_paytech.task.R
import com.valor_paytech.task.ui.post.PostFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailFragment : BaseFragment<FragmentUserDetailBinding>() {

    private val userDetailViewModel: UserDetailViewModel by activityViewModels()
    var userId :Int =-1
    /**
     * Create Binding
     */
    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentUserDetailBinding = FragmentUserDetailBinding.inflate(inflater, container, false)


    companion object {
       // const val ARG_1 = "ARG_1"

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            userId = it.getInt("arg_1")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onClick()
        observeUserDetails()


    }
    fun onClick(){
        binding?.ivBack?.setOnClickListener {
            back()
        }

        binding?.btnViewPost?.setOnClickListener {
            var arguments: Bundle
            arguments = Bundle().apply { putInt("arg_1",userId) }
            changeFragment(binding!!.root.id, PostFragment(), arguments, true)
        }
    }

    private fun observeUserDetails() {
        userDetailViewModel.getUserDetails(userId)!!.observe(requireActivity(), Observer { userListResponse ->
            binding?.progressView?.root?.gone()

            userListResponse.let {
                binding?.tvHeader?.text =   it.username
                binding?.tvName?.text =  it.name
                binding?.tvUserName?.text =  it.username
                binding?.tvEmail?.text = it.email
                binding?.tvPhone?.text = it.phone

                binding?.tvAddress?.text = it.address?.street.plus(", ").plus(it.address?.city)
                    .plus(", ").plus(it.address?.zipcode)
                binding?.tvWebsite?.text = it.website
                binding?.tvCompany?.text = it.company?.name
            }

        })
    }

}
