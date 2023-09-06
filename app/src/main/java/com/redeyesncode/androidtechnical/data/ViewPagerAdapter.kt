package com.redeyesncode.androidtechnical.data

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.redeyesncode.androidtechnical.ui.activity.fragments.FragmentFavorites
import com.redeyesncode.androidtechnical.ui.activity.fragments.FragmentPost

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 2 // Number of tabs
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FragmentPost()
            1 -> FragmentFavorites()
            else -> throw IllegalArgumentException("Invalid position")
        }
    }
}
