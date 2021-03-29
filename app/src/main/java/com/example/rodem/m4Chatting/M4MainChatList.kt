package com.example.rodem.m4Chatting

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.rodem.databinding.M4MainChatlistBinding
import com.google.firebase.firestore.ListenerRegistration

class M4MainChatList : Fragment()//,
        //M51ChattingRoomListAdapter.ItemClickListener,
        //M51ChattingRoomListAdapter.ItemLongClicklistner,
        //M51ChattingRoomListAdapter.ItemClickListenerSuper,
        //M51ChattingRoomListAdapter.ItemImageClickListener
{
    companion object{
        var m5ChattingListsnapShotListenr: ListenerRegistration? = null
    }




    /*var disposableUserPrivateData: Disposable? = null

    private val chatViewModel: ChattingViewModel by viewModels {
        ChatViewModelFactory((activity?.application as M01GlobalApplicationV2).chatRepository)
    }

    private val m2ViewModel: M2MainViewmodel by viewModels {
        M2MainViewmodelFactory((activity?.application as M01GlobalApplicationV2).cardRepository)
    }


    private var chatListerIsReady = false
    fun moveChatInside(data: Any) //외부에서 특정한 대화창으로 들어가게 만들어주는 모듈
    {
        var activeOneTime = true
        fun chatListerIsReady() : Boolean {
            return chatListerIsReady
        }
        functionWaitingUntilExecuted(
                time = 200,
                {
                    chatListerIsReady()
                },
                { response ->
                    if (activeOneTime && response) {
                        context?.startActivity<M5ChatInside>("roomdata" to data)
                        activeOneTime = false
                    }
                }
        )
    }


    private lateinit var layoutManager: RecyclerView.LayoutManager
    private var chatDataInitialized = false
    private var chatRoomDataInitialized = false
    private var chatCacheLastTime: Date = Date(1)   // 이걸 조절하면 데이터 삭제후 들어오는 데이터 기준을 설정가능
    private val adapter
            by lazy {
                M51ChattingRoomListAdapter(requireContext())
                        .apply {
                            setItemClickListener(this@M4MainChatList)
                            setItemLongClikListner(this@M4MainChatList)
                            setItemClickListenerSuper(this@M4MainChatList)
                            setImageClickListener(this@M4MainChatList)
                        }
            }


    override fun onItemClick(detail_data: MutableMap<String, Any>) {
        Toast.makeText(context,"그냥 아이탬 클릭",Toast.LENGTH_SHORT).show()

    }

    //detail 채팅방으로 들어가는 클릭메소드
    override fun onItemClickSuper(detail_data: MutableMap<String, String>) {
        //Toast.makeText(context,"슈퍼 클릭",Toast.LENGTH_SHORT).show()
        startChattingActivity(requireContext(), detail_data)
    }
    override fun onItemLongClick(detail_data: MutableMap<String, Any>) {
        //Toast.makeText(context, "롱클릭", Toast.LENGTH_SHORT).show()
    }


    //이미지를 클릭했을때, 상대방의 프로필을 조회시켜준다.
    override fun onImageItemClick(detail_data: MutableMap<String, String>) {
        val partnerFid = detail_data["partnerFid"] as String
        var disposable : Disposable? = null
        disposable = m2ViewModel.getSpecificHistorySendCard(partnerFid).take(1)
                .subscribe { cardHistoryInfo ->
                    if (cardHistoryInfo.isNotEmpty()) {
                        val mutableMap = cardHistoryInfoToMutableMap(cardHistoryInfo[0])
                        mutableMap["from"] = "chat"
                        val intent = Intent(context, M2CardDetailActivity::class.java)
                        intent.putExtra("CardInfoData", mutableMap as HashMap) // 1이면 포인트 픽스, 0이면 픽스 취소
                        intent.flags += Intent.FLAG_ACTIVITY_CLEAR_TOP
                        startActivity(intent)
                        disposable?.dispose()
                    }
                    else //response 가 null이면 sqlite에 데이터가 없는 경우이므로 fid로 데이터를 가져와야한다.
                    {
                        colRefCardDetailInfo(partnerFid).get().addOnSuccessListener {
                            getCardHistory(requireContext(),it.documents[0],m2ViewModel,3){ response ->
                                if(response)
                                {
                                    m2ViewModel.getSpecificHistorySendCard(partnerFid)
                                    disposable?.dispose()
                                }
                            }
                        }
                    }
                }

    }*/


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       // this.layoutManager = LinearLayoutManager(context)
       // binding.m5RvChattingList.layoutManager = this.layoutManager
       // binding.m5RvChattingList.adapter = this.adapter
       // binding.m5RvChattingList.itemAnimator = null

        //chatCacheLastTime = timestamptoDate(FieldValue.serverTimestamp())


//        /**************************************************************************
//         *******************스크롤 리프레시 부분************************************
//         **************************************************************************/
//        m5_srl1.setOnRefreshListener {
//
//             Toast.makeText(context, "새로고침합니다", Toast.LENGTH_SHORT).show()
//
//            //이미지 늦게 나오는것 수정 2021 01 17
//            var maxCounter = 0
//
//            m5_srl1.isRefreshing = false
//        }
//        /**************************************************************************
//         *******************스크롤 리프레시 부분 끝 ************************************
//         **************************************************************************/


        /************************
         ****툴바 레이아웃 처리****
         *************************/
        with(binding.m5MainChatlistIncludeA0toolbarlayout)
        {
            /**스토어 열기 *//*
            fun activitycall()
            {
                val intent = Intent(context, A7AMain::class.java)
                intent.flags += Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
            }

            //스토어 포인트
            a0CommonToolbarlayoutAblClo1Llo1.visibility = View.VISIBLE
            a0CommonToolbarlayoutAblClo1Llo1TvStorepoint.text = GlobalAppInfo.userPrivatedata.storePoint.toString()

            //스토어 이미지 설정
            //Glide.with(this).load(R.drawable.store_balloons_128).into(m2_main_include_a0toolbarlayout.a0CommonToolbarlayoutAblClo1Llo1AcvStorepoint) => 이렇게 쓰면 동작이 이상해진다.
            a0CommonToolbarlayoutAblClo1Llo1AcvStorepoint.setImageResource(R.drawable.store_balloons_128)


            a0CommonToolbarlayoutAblClo1Llo1.setOnClickListener {
                activitycall()
            }

            a0CommonToolbarlayoutAblClo1Llo1AcvStorepoint.setOnClickListener {
                activitycall()
            }

            a0CommonToolbarlayoutAblClo1Llo1TvStorepoint.setOnClickListener {
                activitycall()
            }
            //타이틀 설정
            a0CommonToolbarlayoutAblClo1TvPagetitle.visibility = View.VISIBLE
            //a0CommonToolbarlayoutAblClo1TvPagetitle.text = GlobalAppInfo.userBasicdata.nickname
            a0CommonToolbarlayoutAblClo1TvPagetitle.text = "메세지"
*/
        }
        /**************************
         ****툴바 레이아웃 처리 끝***
         *************************/


        /*if(userBasicdata.certification0 ==0)// 오류
        {
            binding.m5MainNestedView.visibility =View.GONE
            binding.m5MainNestedViewNonCertification.visibility = View.VISIBLE
        }else if(userBasicdata.certification0 ==2 || userBasicdata.certification0 ==4)// 인증안됨
        {
            binding.m5MainNestedView.visibility =View.GONE
            binding.m5MainNestedViewNonCertification.visibility = View.VISIBLE
        }else if(userBasicdata.certification0 ==3)// 인증 반려됨
        {
            binding.m5MainNonCertificationTv.text = "프로필 변경을 통해 재심사를 받아보세요!"
            binding.m5MainNestedView.visibility =View.GONE
            binding.m5MainNestedViewNonCertification.visibility = View.VISIBLE
        }
        else if(userBasicdata.certification0 ==1)//인증됨
        {


            //이미지 늦게 나오는것 수정 2021 01 17
            var maxCounter = 0
            chatViewModel.getRoomList(fid)
                    .observe(viewLifecycleOwner) {
                        it.let {

                            dataForChatRoomList = it

                            if (!chatRoomDataInitialized) {

                                for (i in it) {
                                    val roomInfo = mutableMapOf<String, Any>()

                                    //Log.e("logCheck", "i.notReadMessageCount : ${i.notReadMessageCount} ")
                                    if (i.notReadMessageCount ?: 0 > maxCounter) {
                                        maxCounter = i.notReadMessageCount ?: 0
                                    }

                                    roomInfo["count"] = maxCounter
                                    roomInfo["lastmsg"] = i.lastChat ?: ""
                                    dataForChatRoomListInfo[i.chatRoomId] = roomInfo
                                }

                                initPartnerPicture(dataForChatRoomList, requireContext(), chatViewModel) { _ ->
                                    chatRoomDataInitialized = true
                                    adapter.submitList(it)
                                }
                            } else {
                                adapter.submitList(it)
                            }

                        }
                    }


            //채팅데이터 최신 데이터 1개만 들고 오기
            chatViewModel.getMsgUptoDate(fid).observe(viewLifecycleOwner) {
                dataForChat = it
                it.let {
                    if (!chatDataInitialized) { // 초기화가 안된 최초의 로딩상태
                        if (it != null) {  // 최초 로딩이지만 캐시가 존재할 경우

                            chatViewModel.resetChatCount(it.chatRoomId!!)
                            chatCacheLastTime = it.sendTime ?: Date()

                            if(m5ChattingListsnapShotListenr == null){

                                m5ChattingListsnapShotListenr = fsUserPrivateChattingList(fid)
                                        .whereGreaterThan("sendTime", chatCacheLastTime as Any)
                                        .addSnapshotListener { sn, _ ->
                                            if (sn != null) {
                                                chatListerIsReady = true
                                                for (i in sn.documentChanges) {
                                                    when (i.type) {
                                                        DocumentChange.Type.ADDED -> {
                                                            *//**try- catch를 쓰지않으면 튕귈때가 있다**//*
                                                            try {
                                                                chatIncomeProcess(dataForChatRoomList, chatViewModel, i.document, requireContext())
                                                            } catch (e: Exception) {
                                                                val x = 5
                                                            }
                                                        }
                                                        else -> {

                                                        }
                                                    }
                                                }
                                            }
                                        }
                            }

                        } else { // 최초 로딩이면서 캐시도 없을 경우
                            if(m5ChattingListsnapShotListenr == null) {

                                Log.e("logcheck", "m5ChattingListsnapShotListenr call")
                                m5ChattingListsnapShotListenr = fsUserPrivateChattingList(fid).whereGreaterThan("sendTime", chatCacheLastTime as Any)
                                        .addSnapshotListener { sn, e ->
                                            chatListerIsReady = true
                                            if (sn != null) {
                                                for (i in sn.documentChanges) {
                                                    when (i.type) {
                                                        DocumentChange.Type.ADDED -> {
                                                            chatIncomeProcess(dataForChatRoomList, chatViewModel, i.document, requireContext())
                                                        }
                                                        else -> {
                                                        }
                                                    }
                                                }
                                            }
                                        }
                            }
                        }
                        chatDataInitialized = true
                    } else {  // 초기화가 된 상태에서 그뒤의 행동
                        if (it != null) {
                            // chatViewModel.updateChatCount(it[0].chatRoomId!!)
                        }
                    }

                }

            }
        }*/
    }

    private var _binding: M4MainChatlistBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View? {
        _binding = M4MainChatlistBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


//    override fun onCreateView(
//            inflater: LayoutInflater,
//            container: ViewGroup?,
//            savedInstanceState: Bundle?
//    ): View? {
//        //  Log.e("연습", "onCreateView")
//        return inflater.inflate(R.layout.m5_main_chatlist, container, false)
//    }

    override fun onStart() {

        /*if (chatDataInitialized) { // 초기화 이후에 다른 엑티비티로 갔다오면서 다시 리스너 붙여주기

            if (dataForChat != null) {
                chatCacheLastTime = dataForChat?.sendTime?:Date()
            }

            *//*snapShotL = fsUserPrivateChattingList(fid).whereGreaterThan(
                "sendTime",
                chatCacheLastTime as Any
            )
                .addSnapshotListener { sn, _ ->
                    if (sn != null) {
                        for (i in sn.documentChanges) {

                            when (i.type) {
                                DocumentChange.Type.ADDED -> {
                                    chatIncomeProcess(dataForChatRoomList, chatViewModel, i.document, requireContext())
                                }
                                else -> {
                                }
                            }
                        }
                    }
                }*//*
        }

        disposableUserPrivateData = GlobalAppInfo.userPrivateDataObserver.subscribe { response ->
            if (response) {
                with(binding.m5MainChatlistIncludeA0toolbarlayout)
                {
                    a0CommonToolbarlayoutAblClo1Llo1TvStorepoint.text = GlobalAppInfo.userPrivatedata.storePoint.toString()
                }
            }
        }*/

        super.onStart()
    }


    override fun onStop() {
        super.onStop()
        Log.e("logCheck", "counting stop call")
        //disposableUserPrivateData?.dispose()
    }




}