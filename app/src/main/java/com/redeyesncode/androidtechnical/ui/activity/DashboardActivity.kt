package com.redeyesncode.androidtechnical.ui.activity

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.tabs.TabLayoutMediator
import com.redeyesncode.androidtechnical.R
import com.redeyesncode.androidtechnical.base.BaseActivity
import com.redeyesncode.androidtechnical.databinding.ActivityDashboardBinding
import com.redeyesncode.androidtechnical.data.ViewPagerAdapter

class DashboardActivity : BaseActivity() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDashboardBinding.inflate(layoutInflater)

        setupNav()
        setContentView(binding.root)
    }

    private fun setupNav() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController


        val adapter = ViewPagerAdapter(this)
        binding.viewPager2.adapter = adapter
        // Create a TabLayoutMediator to connect TabLayout and ViewPager2
        val tabLayoutMediator = TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
            when (position) {
                0 -> tab.text = "Posts"
                1 -> tab.text = "Favorites"
            }
        }
        tabLayoutMediator.attach()
    }
}