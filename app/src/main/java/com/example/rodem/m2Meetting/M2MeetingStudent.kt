package com.example.rodem.m2Meetting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rodem.a0Common.a0Object.GlobalFirebaseObject
import com.example.rodem.databinding.M2MeetingInnerPageBinding

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
    private lateinit var mLayoutManager: RecyclerView.LayoutManager

    private val mAdapter by lazy{
        context?.let {
            M2MeetingAdapter(testItem)
        }
    }


    private val testItem = mutableListOf<MutableMap<String,Any>>()
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.m2InnerTest.text = "학생들 리사이클러뷰 나올거"


        mLayoutManager = LinearLayoutManager(requireContext(),)
        mAdapter?.setHasStableIds(true)
        binding.m2InnerRv.layoutManager = mLayoutManager
        binding.m2InnerRv.adapter = mAdapter
        binding.m2InnerSwipe.setOnRefreshListener {
            GlobalFirebaseObject.colMeetingPosting().limit(10).get().addOnSuccessListener {
                for (i in it){
                    testItem.add(i.data)
                }

                mAdapter?.notifyDataSetChanged()
                binding.m2InnerSwipe.isRefreshing = false
            }

        }


    }


}