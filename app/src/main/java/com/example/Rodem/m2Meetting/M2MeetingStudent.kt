package com.example.Rodem.m2Meetting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.Rodem.databinding.M2MeetingInnerPageBinding

class M2MeetingStudent :Fragment() {
    private var _binding: M2MeetingInnerPageBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = M2MeetingInnerPageBinding.inflate(inflater,container,false)
        val view =binding.root
        return view
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.m2InnerTest.text = "학생들 리사이클러뷰 나올거"
    }


}