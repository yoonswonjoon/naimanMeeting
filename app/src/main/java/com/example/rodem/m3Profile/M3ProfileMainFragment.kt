
package com.example.rodem.m3Profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.rodem.databinding.M3ProfileMainBinding

class M3ProfileMainFragment :Fragment() {

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



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        with(binding){



        }

    }


}