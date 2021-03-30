package com.example.rodem.m2Meeting.category

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rodem.a0Common.a0Object.GlobalFirebaseObject
import com.example.rodem.a0Common.a11ViewControl.WrapContentLinearLayoutManager
import com.example.rodem.databinding.M2MeetingInnerPageBinding
import com.example.rodem.m2Meeting.M2MeetingAdapter
import com.example.rodem.m2Meeting.M2MeetingIntro
import com.google.firebase.firestore.Query
import vlm.naimanmaster.a1Functions.a13DateControl.timestamptoDate
import java.util.*
import kotlin.collections.HashMap

class M2MeetingStudent : Fragment(), M2MeetingAdapter.ItemClickListener {
    private var _binding: M2MeetingInnerPageBinding? = null
    private val binding get() = _binding!!
    private var dateTrackerStart = Date() // 최근것

    private var dateTrackerEnd = Date() // 젤 아래것


    /**페이지 네이션 관련*/
    var dataLoadingState = false //false이면 로딩이 완료/혹은 중지 된 상태, true이면 로딩중인 상태
    private val lastVisibleItemPosition: Int get() = (m2MeetingRv.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
    private lateinit var m2MeetingRv: RecyclerView

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = M2MeetingInnerPageBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }

    private lateinit var mLayoutManager: RecyclerView.LayoutManager

    private val mAdapter by lazy {
        context?.let {
            M2MeetingAdapter(testItem).apply { setItemClickListener(this@M2MeetingStudent) }
        }
    }

    override fun onItemClick(detail_data: MutableMap<String, Any>) {
        val data = detail_data as HashMap<String, Any>
        val intent = Intent(requireContext(), M2MeetingIntro::class.java)
        intent.putExtra("detailData", data)
        startActivity(intent)
    }


    private val testItem = mutableListOf<MutableMap<String, Any>>()
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //binding.m2InnerTest.text = "학생들 리사이클러뷰 나올거"


        /**최초 아이탬 불러오기*///.whereEqualTo("category",1)
        GlobalFirebaseObject.colMeetingStudentPosting()
                .orderBy("date", Query.Direction.DESCENDING).limit(5).get().addOnSuccessListener {
                    for (i in it) {
                        testItem.add(i.data)
                    }

                    if (!it.isEmpty) {
                        dateTrackerStart = timestamptoDate(testItem[0]["date"])
                        dateTrackerEnd = timestamptoDate(testItem[testItem.size - 1]["date"])
                    } // 맨 위의 게시물 date를 추적

                    mAdapter?.notifyDataSetChanged()
                }


        m2MeetingRv = binding.m2InnerRv
        mLayoutManager = WrapContentLinearLayoutManager(requireContext())
        mAdapter?.setHasStableIds(true)
        m2MeetingRv.layoutManager = mLayoutManager
        m2MeetingRv.adapter = mAdapter
        setRecyclerViewScrollListener(m2MeetingRv)


        binding.m2InnerSwipe.setOnRefreshListener {
            binding.m2InnerSwipe.isEnabled = false
            GlobalFirebaseObject.colMeetingStudentPosting().whereGreaterThan("date", dateTrackerStart)
                    .orderBy("date", Query.Direction.DESCENDING).get().addOnSuccessListener {
                        val temporaryItemContainer = mutableListOf<MutableMap<String, Any>>()
                        temporaryItemContainer.addAll(testItem)
                        testItem.clear()
                        for (i in it) {
                            testItem.add(i.data)
                        }
                        testItem.addAll(temporaryItemContainer)
                        if (!it.isEmpty) {
                            dateTrackerStart = timestamptoDate(testItem[0]["date"])
                        }

                        Log.e("adapter",mAdapter?.itemCount.toString())
                        Log.e("adapter",testItem.size.toString())


                        mAdapter?.notifyDataSetChanged()
                        binding.m2InnerSwipe.isRefreshing = false
                        binding.m2InnerSwipe.isEnabled = true
                    }

        }


    }


    //스크롤을 통해 해당 댓글들을 가져오는 리스너 관리 함수
    private fun setRecyclerViewScrollListener(Target_recyclerView: RecyclerView) {
        Target_recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val totalItemCount = recyclerView.layoutManager!!.itemCount

//                 Log.e("checkfor", "totalItemCount : $totalItemCount, " +
//                         "/lastVisibleItemReversePosition : $lastCompletelyVisibleItemReversePosition " +
//                         "/lastVisibleItemPosition : ${lastVisibleItemPosition + 1}" +
//                         "/firstVisibleItemReversePosition : $firstCompletelyVisibleItemReversePosition " +
//                         "/firstVisibleItemPosition : ${firstVisibleItemPosition + 1}"
//                 )

                if (!dataLoadingState && totalItemCount == lastVisibleItemPosition + 1) { //5는 버퍼이다.
                    dataLoadingState = true
                    postingLoder()
                }
            }
        })
    }

    fun postingLoder(newone: Boolean = false) {

        //val temporaryItemContainer = mutableListOf<MutableMap<String,Any>>()
        //temporaryItemContainer.addAll(testItem)
        //testItem.clear()
        GlobalFirebaseObject.colMeetingStudentPosting().whereLessThan("date", dateTrackerEnd)
                .orderBy("date", Query.Direction.DESCENDING).limit(5).get().addOnSuccessListener {
                    for (i in it) {
                        testItem.add(i.data)
                    }
                    //testItem.addAll(temporaryItemContainer)
                    if (!it.isEmpty) {

                        dateTrackerEnd = timestamptoDate(testItem[testItem.size - 1]["date"])
                    }
                    mAdapter?.notifyDataSetChanged()
                    dataLoadingState = false
                }

        /*chatViewModel.getSpecificRoomMessagePagenation(roomid, fid, chattingPagenationPosition, 50)
          { newListItems ->
              newListItems.let { newNotnullListItems ->
                  chatDataloadingState = false
                  chatMsgList.addAll(newNotnullListItems)
                  chatMsgList.sortByDescending { it.sendTime ?: Date() }
                  val chatMsgListUnique = chatMsgList.distinct()
                  chatMsgList.clear()
                  chatMsgList.addAll(chatMsgListUnique)
                  madapterMsg.submitList(chatMsgList) //distinct는 중복을 제거한다.
                  chattingPagenationPosition += newListItems.size
                  //Log.e("checkfor", "newItem : ${newListItems.size} // chattingPagenationPosition : $chattingPagenationPosition")
                  madapterMsg.notifyItemRangeInserted(chattingPagenationPosition + 1, newListItems.size)
                  if (newone) {
                      chattingRecyclerview.scrollToPosition(0)
                  }
              }
          }*/
    }


}