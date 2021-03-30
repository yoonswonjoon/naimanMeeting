package com.example.rodem.m2Meeting

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rodem.a0Common.a0Object.GlobalDataSet.basicBackgroundDataSet
import com.example.rodem.databinding.M2MeetingIntroBinding
import vlm.naimanmaster.a1Functions.a13DateControl.firebaseTimeConverter

class M2MeetingIntro : AppCompatActivity() {

    private lateinit var binding: M2MeetingIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




        binding = M2MeetingIntroBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        val detailData = intent.getSerializableExtra("detailData") as HashMap<*, *>

        val randomBackground = detailData["randomBackground"]?.toString()?.toInt() ?: 0


        // 배경 설정
        binding.m2MeetingIntroMainClo.setBackgroundResource(basicBackgroundDataSet[randomBackground])
        with(binding) {
            if (randomBackground == 3)//밝은 이미지
            {
                m2MeetingIntroParticipantNum.setTextColor(Color.BLACK)
                m2MeetingIntroTitle.setTextColor(Color.BLACK)
                m2MeetingIntroContents.setTextColor(Color.BLACK)
                m2MeetingDate.setTextColor(Color.BLACK)

            } else {//어두운 이미지
                m2MeetingIntroClose.setColorFilter(Color.WHITE)
            }
        }


        val dayList = (detailData["days"]?.toString()?: ",").split(",")
        // 유아이 글자 체우기
        with(binding) {
            m2MeetingIntroTitle.text = detailData["title"]?.toString()?: ""
            m2MeetingIntroPlace.text = "# ${(detailData["place"]?.toString()?: "").replace(",", "  #")}"
            m2MeetingIntroContents.text = detailData["contents"]?.toString() ?: ""
            m2MeetingIntroParticipantNum.text = detailData["participantNum"]?.toString() ?: ""

            m2MeetingIntroDays.text = dayReturner(dayList)
            m2MeetingDate.text = firebaseTimeConverter(date=detailData["date"],DateFormat =  "MM/dd HH:mm")

            m2MeetingApply.setOnClickListener {

            }
            m2MeetingIntroClose.setOnClickListener {
                finish()
            }
        }
    }


    private fun dayReturner(dayList:List<String>):String{
        var returnString = ""
        val dayStringList = listOf<String>("","월","화","수","목","금","토","일")

        for (i in dayList){
            returnString += " ${dayStringList[i.toString().toInt()]}"
        }
        return returnString
    }
}