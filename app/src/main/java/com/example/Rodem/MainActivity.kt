package com.example.Rodem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.Rodem.databinding.ActivityMainBinding
import com.example.Rodem.m2Meetting.M2MeetingMainFragment
import com.example.Rodem.m3Profile.M3ProfileMainFragment
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity() {

    private lateinit var binding :ActivityMainBinding
    private lateinit var mainVp :ViewPager2
    private val m2MeetingFragment = M2MeetingMainFragment()
    private val m3ProfileFragment = M3ProfileMainFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val mainVpAdapter = MainVpAdapter(this,2)
        with(binding){
            mainVp = mainViewpager
            mainVp.bringToFront()
            mainVp.adapter = mainVpAdapter
            mainVp.offscreenPageLimit =2
            mainVp.isUserInputEnabled = false
        }

        TabLayoutMediator(binding.mainTab,mainVp,false,false){tab, position ->
            when(position){
                0->{
                    tab.text = "미팅구하기"
                }
                else->{
                    tab.text = "내정보"
                }
            }
        }.attach()



    }


    private inner class MainVpAdapter(fa : FragmentActivity,itemCount : Int):FragmentStateAdapter(fa){
        val count = itemCount
        override fun getItemCount(): Int {
            return count
        }

        override fun createFragment(position: Int): Fragment {
           return when(position){
                0->{
                    m2MeetingFragment
                }else->{
                   m3ProfileFragment
                }
            }
        }


    }




}