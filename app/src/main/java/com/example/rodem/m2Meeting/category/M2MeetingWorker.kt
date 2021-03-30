package com.example.rodem.m2Meeting.category

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rodem.a0Common.a0Object.GlobalFirebaseObject
import com.example.rodem.databinding.M2MeetingInnerPageBinding
import com.example.rodem.m2Meeting.M2MeetingAdapter
import com.example.rodem.m2Meeting.M2MeetingIntro
import com.google.firebase.firestore.Query
import vlm.naimanmaster.a1Functions.a13DateControl.timestamptoDate
import java.util.*

class M2MeetingWorker :Fragment(),M2MeetingAdapter.ItemClickListener {
    private var _binding: M2MeetingInnerPageBinding?=null
    private val binding get() = _binding!!
    private var dateTracker = Date()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = M2MeetingInnerPageBinding.inflate(inflater,container,false)
        val view =binding.root
        return view
    }

    override fun onItemClick(detail_data: MutableMap<String, Any>) {
        val data = detail_data as HashMap<String,Any>
        val intent = Intent(requireContext(), M2MeetingIntro::class.java)
        intent.putExtra("detailData",data)
        startActivity(intent)
    }


    private lateinit var mLayoutManager: RecyclerView.LayoutManager
    private val testItem = mutableListOf<MutableMap<String,Any>>()
    private val mAdapter by lazy{
        context?.let {
            M2MeetingAdapter(testItem).apply { setItemClickListener(this@M2MeetingWorker) }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //binding.m2InnerTest.text = "직장인들 리사이클러뷰 나올거"

        /**최초 아이탬 불러오기*///.whereEqualTo("category",1)
        GlobalFirebaseObject.colMeetingWorkerPosting().orderBy("date", Query.Direction.DESCENDING).limit(10).get().addOnSuccessListener {
            for (i in it){
                testItem.add(i.data)
            }

            if(!it.isEmpty){
                dateTracker = timestamptoDate(testItem[0]["date"])
            } // 맨 위의 게시물 date를 추적

            mAdapter?.notifyDataSetChanged()
        }


        mLayoutManager = LinearLayoutManager(requireContext(),)
        mAdapter?.setHasStableIds(true)
        binding.m2InnerRv.layoutManager = mLayoutManager
        binding.m2InnerRv.adapter = mAdapter
        binding.m2InnerSwipe.setOnRefreshListener {
            val temporaryItemContainer = mutableListOf<MutableMap<String,Any>>()
            temporaryItemContainer.addAll(testItem)
            testItem.clear()

            GlobalFirebaseObject.colMeetingWorkerPosting().whereGreaterThan("date",dateTracker)
                    .orderBy("date",Query.Direction.DESCENDING).get().addOnSuccessListener {


                        for (i in it){
                            testItem.add(i.data)
                        }
                        testItem.addAll(temporaryItemContainer)
                        if(!it.isEmpty){
                            dateTracker = timestamptoDate(testItem[0]["date"])
                        }
                        mAdapter?.notifyDataSetChanged()
                        binding.m2InnerSwipe.isRefreshing = false
                    }

        }
    }


}