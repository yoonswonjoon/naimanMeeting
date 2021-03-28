package com.example.rodem

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.rodem.databinding.ActivityMainBinding
import com.example.rodem.m1Writing.M1MeetingWriting
import com.example.rodem.m2Meeting.M2MeetingMainFragment
import com.example.rodem.m3Profile.M3ProfileMainFragment
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback


class MainActivity : AppCompatActivity() {

    private lateinit var binding :ActivityMainBinding
    private lateinit var mainVp :ViewPager2
    private val m2MeetingFragment = M2MeetingMainFragment()
    private val m3ProfileFragment = M3ProfileMainFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

      // window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
      // window.exitTransition =Explode()


        setExitSharedElementCallback(MaterialContainerTransformSharedElementCallback())
        window.sharedElementsUseOverlay = false


        val view = binding.root
        setContentView(view)

        val mainVpAdapter = MainVpAdapter(this, 2)
        with(binding){
            mainVp = mainViewpager
            mainVp.bringToFront()
            mainVp.adapter = mainVpAdapter
            mainVp.offscreenPageLimit =2
            mainVp.isUserInputEnabled = false




            btnTest.setOnClickListener {
                val intent = Intent(this@MainActivity, M1MeetingWriting::class.java)


                val activateOption = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        this@MainActivity, binding.btnTest, ViewCompat.getTransitionName(binding.btnTest)!!
                )


                //ActivityOptionsCompat.makeClipRevealAnimation()

                //startActivity(intent,activateOption)
                ActivityCompat.startActivity(this@MainActivity, intent, activateOption.toBundle())
            }


        }



        TabLayoutMediator(binding.mainTab, mainVp, false, false){ tab, position ->
            when(position){
                0 -> {
                    tab.text = "미팅구하기"
                }
                else->{
                    tab.text = "내정보"
                }
            }
        }.attach()



    }


    private inner class MainVpAdapter(fa: FragmentActivity, itemCount: Int):FragmentStateAdapter(fa){
        val count = itemCount
        override fun getItemCount(): Int {
            return count
        }

        override fun createFragment(position: Int): Fragment {
           return when(position){
               0 -> {
                   m2MeetingFragment
               }else->{
                   m3ProfileFragment
                }
            }
        }


    }




}