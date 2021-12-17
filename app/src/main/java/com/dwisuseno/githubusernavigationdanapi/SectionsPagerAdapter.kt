package com.dwisuseno.githubusernavigationdanapi

import android.os.Bundle
import android.text.style.TtsSpan.ARG_USERNAME
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    var username: String? = null

    companion object {
        const val EXTRA_USERNAME = "extra_username"
        private val TAG = "BundleFragment"
    }

    override fun getItemCount(): Int {
        return 2
    }


    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        val bundle = Bundle()
        when (position) {
            0 -> {
                fragment = FollowerFragment()
                bundle.putString(EXTRA_USERNAME, username)
                fragment.arguments = bundle
            }
            1 -> {
                fragment = FollowingFragment()
                bundle.putString(EXTRA_USERNAME, username)
                fragment.arguments = bundle
            }
        }
        return fragment as Fragment
    }



}