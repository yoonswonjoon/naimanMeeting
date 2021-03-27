package com.example.Rodem.m1Writing

import android.graphics.Color
import android.os.Bundle
import android.widget.CheckedTextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import com.example.Rodem.R
import com.example.Rodem.databinding.M1WritingMainBinding
import com.google.android.material.transition.platform.MaterialContainerTransform
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback


class M1MeetingWriting : AppCompatActivity(), M1WritingDialogPicker.DialogListener {

    private lateinit var binding: M1WritingMainBinding


    override fun sendPickerDialogRespond(dataInt: Int, respond: Boolean) {
        if (respond) {
            binding.m1WritingParticipantNum.text = dataInt.toString()
        } else {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = M1WritingMainBinding.inflate(layoutInflater)

        /**엑티비티 에니메이션 등록*/
        setEnterSharedElementCallback(MaterialContainerTransformSharedElementCallback())
        val transform = MaterialContainerTransform()
        transform.addTarget(binding.m1WritingMain)
        /*transform.setAllContainerColors(
            MaterialColors.getColor(
                findViewById(R.id.content),
                R.attr.colorSurface
            )
        )*/
        transform.fitMode = MaterialContainerTransform.FIT_MODE_AUTO
        transform.duration = 400L
        //transform.pathMotion = MaterialArcMotion()
        transform.interpolator = FastOutSlowInInterpolator()
        transform.scrimColor = Color.parseColor("#ffffff")

        window.sharedElementEnterTransition = transform
        /**엑티비티 에니메이션 등록 끝*/
        val view = binding.root
        setContentView(view)


        /**앱 바 등록*/
        setSupportActionBar(findViewById(R.id.a0_common_app_bar))
        supportActionBar?.title = "글쓰기"

        with(binding.m1WritingAppBar) {
            a0AppBarClose.setOnClickListener {
                onBackPressed()
            }

            a0AppBarEndTv.text = "글 올리기"
        }

        /**앱 바 등록 끝*/

        val dayList = mutableListOf<Int>()


        with(binding) {
            m1CheckMonday.setOnClickListener {
                (it as CheckedTextView).toggle()
                if (it.isChecked) {
                    dayList.add(1)
                } else {
                    dayList.remove(1)
                }
            }


            m1CheckTuesday.setOnClickListener {
                (it as CheckedTextView).toggle()
                if (it.isChecked) {
                    dayList.add(2)
                } else {
                    dayList.remove(2)
                }
            }
            m1CheckWednesday.setOnClickListener {
                (it as CheckedTextView).toggle()
                if (it.isChecked) {
                    dayList.add(3)
                } else {
                    dayList.remove(3)
                }
            }
            m1CheckThursday.setOnClickListener {
                (it as CheckedTextView).toggle()
                if (it.isChecked) {
                    dayList.add(4)
                } else {
                    dayList.remove(4)
                }
            }
            m1CheckFriday.setOnClickListener {
                (it as CheckedTextView).toggle()
                if (it.isChecked) {
                    dayList.add(5)
                } else {
                    dayList.remove(5)
                }
            }
            m1CheckSaturday.setOnClickListener {
                (it as CheckedTextView).toggle()
                if (it.isChecked) {
                    dayList.add(6)
                } else {
                    dayList.remove(6)
                }
            }
            m1CheckSunday.setOnClickListener {
                (it as CheckedTextView).toggle()
                if (it.isChecked) {
                    dayList.add(7)
                } else {
                    dayList.remove(7)
                }
            }
        }

        binding.m1WritingAppBar.a0AppBarEndTv.setOnClickListener {
            val titleText = binding.m1WritingTitle.text.toString()
            val contentsText = binding.m1WritingContent.text.toString()
            val place1 = binding.m1WritingPlace1.text.toString()
            val place2 = binding.m1WritingPlace2.text.toString()
            val place3 = binding.m1WritingPlace3.text.toString()
            val participantNum = binding.m1WritingParticipantNum.text.toString().toInt()

            val checkBoolean = checkPossibilityUpload(title=titleText,contents = contentsText,
            place1 = place1,place2 = place2,place3 = place3,participantNum = participantNum,days = dayList)

            if(checkBoolean){ //올릴수 있다
                val firebaseData = forFirebaseDataMapMaker(title=titleText,contents = contentsText,
                        place1 = place1,place2 = place2,place3 = place3,participantNum = participantNum,days = dayList)

                Toast.makeText(this,"파이어베이스로 넘기기 만들어야함",Toast.LENGTH_SHORT).show()
            }else{// 부족함
                Toast.makeText(this,"필수 요소를 작성하세요",Toast.LENGTH_SHORT).show()
            }

        }


        binding.m1WritingParticipantNum.setOnClickListener {
            val editDialog = M1WritingDialogPicker()
            editDialog.show(supportFragmentManager, "112")
        }
    }


    /**작성되야하는 필수 작성란이 다 작성 되었는지 체크  다 되있다(true)*/
    private fun checkPossibilityUpload(
        title: String, contents: String, place1: String, place2: String, place3: String,
        participantNum: Int, days: MutableList<Int>
    ): Boolean {
        val place = mutableListOf<String>() //장소들 리스트
        if (onlyLetterCount(place1) != 0) place.add(place1.trim())
        if (onlyLetterCount(place2) != 0) place.add(place2.trim())
        if (onlyLetterCount(place3) != 0) place.add(place3.trim())
        return !(onlyLetterCount(title) == 0 ||
                onlyLetterCount(contents) == 0 ||
                place.isEmpty() ||
                days.isEmpty() ||
                participantNum == 0)
    }


    private fun forFirebaseDataMapMaker(
        title: String, contents: String, place1: String, place2: String, place3: String,
        participantNum: Int, days: MutableList<Int>
    ): MutableMap<String, Any> {
        val place = mutableListOf<String>() //장소들 리스트
        if (onlyLetterCount(place1) != 0) place.add(place1.trim())
        if (onlyLetterCount(place2) != 0) place.add(place2.trim())
        if (onlyLetterCount(place3) != 0) place.add(place3.trim())


        //장소를 ㅁㅁㅁ,ㅈㅈㅈ,ㅇㅇㅇ 로만들기 : 파이어베이스 리딩수 줄임
        var placeString = ""
        placeString = if(place.size ==1){
            place[0]
        }else if(place.size ==2){
            place[0]+",${place[1]}"
        }else {
            place[0]+",${place[1]}"+",${place[2]}"
        }

        // 요일을 숫자로 표현해서 정렬해주고 1,3,5이렇게 만들어줌
        val orderedDays = days.sorted()
        var daysString = ""
        if(days.size ==1){
            daysString = "${days[0]}"
        }else{
            for (i in 0 until days.size){
                if(i ==0){
                    daysString = "${days[i]}"
                }else{
                    daysString+=",${days[i]}"
                }
            }
        }



        val resultMap = mutableMapOf<String,Any>()
        resultMap["title"] = title
        resultMap["contents"] = contents
        resultMap["place"] = placeString
        resultMap["participantNum"] = participantNum
        resultMap["days"] = daysString

        return resultMap
    }


    private fun removeVacancy(contents: String): String {
        return contents.replace(" ", "")
    }

    private fun onlyLetterCount(contents: String): Int {
        return removeVacancy(contents).length
    }


}