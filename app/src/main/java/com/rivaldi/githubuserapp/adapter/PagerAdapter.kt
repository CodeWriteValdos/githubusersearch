package com.rivaldi.githubuserapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class PagerAdapterActivity(
    fragment: FragmentActivity,
    private val fragmentCreators: List<Fragment>
) : FragmentStateAdapter(fragment) {

    override fun getItemCount() = fragmentCreators.size

    override fun createFragment(position: Int): Fragment {
        return fragmentCreators[position]
    }

}