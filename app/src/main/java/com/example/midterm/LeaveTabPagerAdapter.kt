package com.example.midterm

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class LeaveTabPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 3
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ReviewFragment()
            1 -> ApprovedFragment()
            2 -> RejectedFragment()
            else -> Fragment()
        }
    }
}

