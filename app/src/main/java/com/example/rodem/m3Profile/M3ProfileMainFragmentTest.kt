
package com.example.rodem.m3Profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.rodem.a0Common.a0Object.GlobalFirebaseObject.fireStore
import com.example.rodem.databinding.M3ProfileMainBinding
import com.google.api.Distribution
import com.google.firebase.firestore.SetOptions
import java.lang.Exception

class M3ProfileMainFragmentTest :Fragment() {

    private lateinit var meetingVp :ViewPager2
    private var _binding: M3ProfileMainBinding?=null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = M3ProfileMainBinding.inflate(inflater,container,false)
        val view =binding.root
        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private var userId = 1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        with(binding){

            /**아이디 체크*/
            m3Id1.setOnClickListener {
                userId =1
            }
            m3Id2.setOnClickListener {
                userId =2
            }

            /**지역 선택*/
            m3DistrictBtn.setOnClickListener{
                val districtData = mutableMapOf<String,Any>()
                districtData["district"] = try {
                    m3DistrictText.text.toString().toInt()
                }catch (e:Exception){
                    0
                }
                fireStore.collection("user").document("$userId").set(districtData, SetOptions.merge())
                        .addOnSuccessListener {  }


            }


        }

    }


}