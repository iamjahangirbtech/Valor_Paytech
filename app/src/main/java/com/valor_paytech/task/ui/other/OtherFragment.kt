package com.valor_paytech.task.ui.other

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.valor_paytech.task.base.BaseFragment
import com.valor_paytech.task.databinding.FragmentOtherBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OtherFragment : BaseFragment<FragmentOtherBinding>() {

    lateinit var page:String
    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentOtherBinding = FragmentOtherBinding.inflate(inflater, container, false)

    companion object {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            page = it.getString("arg_1").toString()
            binding?.tvName?.text = page
        }
    }



}
