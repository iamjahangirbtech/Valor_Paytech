package com.valor_paytech.task.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.manager.ConnectivityMonitor
import com.codingwithjks.customlivedata.Connectivity.ConnectivityLiveData
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.valor_paytech.task.R
import com.valor_paytech.task.base.BaseActivity
import com.valor_paytech.task.databinding.ActivityMainBinding
import com.valor_paytech.task.ui.other.OtherFragment
import com.valor_paytech.task.ui.user_listing.UserListingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    lateinit var bottomNavigationView: BottomNavigationView
    private var fragments: MutableList<Fragment>? = null
    private lateinit var connectivityLiveData:ConnectivityLiveData
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //changeFragment(binding.root.id, UserListingFragment(),null,false)
        initBottombar()

        connectivityLiveData= ConnectivityLiveData(application)
        connectivityLiveData.observe(this, Observer {isAvailable->
            when(isAvailable)
            {
                true-> Toast.makeText(this, "Connected with Internet", Toast.LENGTH_SHORT).show()
                false-> Toast.makeText(this, "No Network", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun initBottombar(){
        bottomNavigationView = binding.vMain.bottomNavView

        bottomNavigationView.setOnNavigationItemSelectedListener(
            mOnNavigationItemSelectedListener
        )
        bottomNavigationView.setItemIconTintList(null)
        bottomNavigationView.background = null
        bottomNavigationView.menu.getItem(2).isEnabled = false


        changeFragment(R.id.frame_container, UserListingFragment(),null,false)


        binding.vMain.fabBot.setOnClickListener {
            bottomNavigationView.getMenu().setGroupCheckable(0, false, true);
        }
    }

    var selectedFragment: Fragment? = null
    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            var arguments: Bundle? = null
            when (item.itemId) {
                R.id.menu_home -> {
                    arguments = Bundle().apply { putString("arg_1", "") }
                    selectedFragment = UserListingFragment()
                }
                R.id.menu_shorts -> {
                    arguments = Bundle().apply { putString("arg_1", "Shorts") }
                    selectedFragment = OtherFragment()
                }
                R.id.menu_subscriptions -> {
                    arguments = Bundle().apply { putString("arg_1", "Subscriptions") }
                    selectedFragment = OtherFragment()
                }

                R.id.menu_library -> {
                    arguments = Bundle().apply { putString("arg_1", "Library") }
                    selectedFragment = OtherFragment()
                }
            }
           // KeyboardUtils.hideKeyboard(this@MainActivity)


            changeFragment(R.id.frame_container, selectedFragment!!, arguments, true)
            bottomNavigationView.getMenu().setGroupCheckable(0, true, true);
            true
        }
    override fun createBinding(): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)




}