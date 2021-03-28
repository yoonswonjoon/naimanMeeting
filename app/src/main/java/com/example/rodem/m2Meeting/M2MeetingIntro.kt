package com.example.rodem.m2Meeting

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rodem.databinding.M2MeetingIntroBinding

class M2MeetingIntro : AppCompatActivity() {

    private lateinit var binding :M2MeetingIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = M2MeetingIntroBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val detailData = intent.getSerializableExtra("detailData") as HashMap<*,*>

        binding.m2MeetingIntroTitle.text = detailData["title"].toString()
        binding.m2MeetingIntroPlace.text = "# ${detailData["place"].toString().replace(",","  #")}"
        binding.m2MeetingIntroContents.text = detailData["contents"].toString()

    }
}