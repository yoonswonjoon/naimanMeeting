package com.example.rodem.m2Meeting

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.rodem.R
import com.example.rodem.databinding.M2MeetingMainBinding
import com.example.rodem.m1Writing.M1MeetingWriting
import com.example.rodem.m2Meeting.category.M2MeetingStudent
import com.example.rodem.m2Meeting.category.M2MeetingWorker
import com.example.rodem.m2Meeting.m2Dialog.M2DistrictDialog
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.database.collection.LLRBNode

class M2MeetingMainFragment :Fragment() {

    private lateinit var meetingVp :ViewPager2
    private var _binding: M2MeetingMainBinding?=null
    private val binding get() = _binding!!

    private val m2StudentPage = M2MeetingStudent()
    private val m2WorkerPage = M2MeetingWorker()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = M2MeetingMainBinding.inflate(inflater,container,false)
        val view =binding.root
        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val pagerAdapter =M2MeetingVpAdapter(this@M2MeetingMainFragment,2)


        with(binding){
            meetingVp = m2MeetingMainViewpager
            meetingVp.adapter = pagerAdapter
            meetingVp.offscreenPageLimit =2
            meetingVp. isUserInputEnabled= false


            m2MeetingMainFab.setOnClickListener {
                val intent = Intent(requireContext(), M1MeetingWriting::class.java)


                val activateOption = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        requireActivity(),binding.m2MeetingMainFab, ViewCompat.getTransitionName(binding.m2MeetingMainFab)!!
                )

                startActivity(intent,activateOption.toBundle())
                //ActivityCompat.startActivity(requireActivity(),intent,activateOption.toBundle())
            }


        }

        binding.m2MeetingMainDistrict.setOnClickListener {
            val districtDialog = M2DistrictDialog()
            fragmentManager?.let { it1 -> districtDialog.show(it1,"112") }

        }




        TabLayoutMediator(binding.m2MeetingMainTab,meetingVp,false,false){tab,position ->
            when(position){
                0->{
                    tab.text = "?????????"

                }
                else->{
                    tab.text = "?????????"
                }
            }
        }.attach()

        binding.m2MeetingMainTab.tabRippleColor = null

    }

   private inner class M2MeetingVpAdapter(fa: Fragment, itemCount : Int):FragmentStateAdapter(fa){
       val count = itemCount

       override fun getItemCount(): Int {
           return count
       }

       override fun createFragment(position: Int): Fragment {
           return when(position){
               0->{
                   m2StudentPage
               }else->{
                   m2WorkerPage
               }
           }
       }

   }
}