package com.example.rodem.m1Writing

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.CheckedTextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import com.example.rodem.R
import com.example.rodem.a0Common.a0Object.GlobalDataSet
import com.example.rodem.a0Common.a0Object.GlobalFirebaseObject.colMeetingStudentPosting
import com.example.rodem.a0Common.a0Object.GlobalFirebaseObject.colMeetingWorkerPosting
import com.example.rodem.a0Common.a11ViewControl.KeyboardVisibilityUtils
import com.example.rodem.a0Common.dialog.A0DistrictDialogPicker
import com.example.rodem.a0Common.dialog.A0TextDialogBasic
import com.example.rodem.databinding.M1WritingMainBinding
import com.google.android.material.transition.platform.MaterialContainerTransform
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback
import com.google.firebase.firestore.FieldValue


class M1MeetingWriting : AppCompatActivity(), M1WritingDialogPicker.DialogListener,
    A0TextDialogBasic.DialogListener,A0DistrictDialogPicker.DialogListener {

    private lateinit var binding: M1WritingMainBinding
    private lateinit var keyboardVisibilityUtils: KeyboardVisibilityUtils
    private var firebaseData = mutableMapOf<String,Any>()
    private var category = 0
    private var district = 0

    override fun sendDistrictPickerDialogRespond(dataInt: Int, respond: Boolean) {
        if (respond) {
            district = dataInt
            binding.m1WritingDistrict.text = GlobalDataSet.districtDataSet[dataInt]
        } else {

        }
    }

    override fun sendPickerDialogRespond(dataInt: Int, respond: Boolean) {
        if (respond) {
            binding.m1WritingParticipantNum.text = dataInt.toString()
        } else {

        }
    }



    override fun fromShortageDialogRespond(respond: Boolean) {
        if(respond){
            binding.m1DialogBasicPb.visibility = View.VISIBLE
            binding.m1DialogBasicPb.progress
            firebaseData["date"] = FieldValue.serverTimestamp()

            if(category ==1){//??????
                colMeetingStudentPosting().document().set(firebaseData).addOnSuccessListener {
                    binding.m1DialogBasicPb.visibility = View.GONE
                    finish()
                    Toast.makeText(this,"?????? ??????",Toast.LENGTH_SHORT).show()
                }.addOnFailureListener {
                    Toast.makeText(this,"?????? ??????",Toast.LENGTH_SHORT).show()
                }
            }else{//?????????
                colMeetingWorkerPosting().document().set(firebaseData).addOnSuccessListener {
                    binding.m1DialogBasicPb.visibility = View.GONE
                    finish()
                    Toast.makeText(this,"?????? ??????",Toast.LENGTH_SHORT).show()
                }.addOnFailureListener {
                    Toast.makeText(this,"?????? ??????",Toast.LENGTH_SHORT).show()
                }
            }




        }else{

        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = M1WritingMainBinding.inflate(layoutInflater)


        /**????????? ???????????? ??????*/
        /*keyboardVisibilityUtils = KeyboardVisibilityUtils(window,
                onShowKeyboard = { keyboardHeight ->
                    binding.m1WritingCloMain.run {
                        smoothScrollBy(scrollX, scrollY + keyboardHeight)
                    }
                })*/


        /**???????????? ??????????????? ??????*/
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
        /**???????????? ??????????????? ?????? ???*/
        val view = binding.root
        setContentView(view)


        /**??? ??? ??????*/
        setSupportActionBar(findViewById(R.id.a0_common_app_bar))
        supportActionBar?.title = "?????????"

        with(binding.m1WritingAppBar) {
            a0AppBarClose.setOnClickListener {
                onBackPressed()
            }

            a0AppBarEndTv.text = "??? ?????????"
        }

        /**??? ??? ?????? ???*/

        val dayList = mutableListOf<Int>()


        with(binding) {
            /** ????????? ?????? ??????*/
            m1WritingStudentRadio.setOnClickListener {
                category =1
            }

            m1WritingJobRadio.setOnClickListener {
                category =2
            }




            /**????????????*/
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

            /**???????????? ???*/
        }


        binding.m1WritingDistrict.text = GlobalDataSet.districtDataSet[district]






        binding.m1WritingParticipantNum.setOnClickListener {
            val editDialog = M1WritingDialogPicker()
            editDialog.show(supportFragmentManager, "112")
        }

        binding.m1WritingDistrict.setOnClickListener {
            val districtDialog = A0DistrictDialogPicker(district)
            districtDialog.show(supportFragmentManager,"112")
        }



        binding.m1WritingAppBar.a0AppBarEndTv.setOnClickListener {
            val titleText = binding.m1WritingTitle.text.toString()
            val contentsText = binding.m1WritingContent.text.toString()
            val place1 = binding.m1WritingPlace1.text.toString()
            val place2 = binding.m1WritingPlace2.text.toString()
            val place3 = binding.m1WritingPlace3.text.toString()
            val participantNum = binding.m1WritingParticipantNum.text.toString().toInt()

            val checkBoolean = checkPossibilityUpload(title=titleText,contents = contentsText,
                    place1 = place1,place2 = place2,place3 = place3,participantNum = participantNum,days = dayList,category)

            if(checkBoolean){ //????????? ??????
                firebaseData = forFirebaseDataMapMaker(title=titleText,contents = contentsText,
                        place1 = place1,place2 = place2,place3 = place3,participantNum = participantNum,days = dayList,category)

                val confirmDialog = A0TextDialogBasic()
                confirmDialog.show(supportFragmentManager,"112")

            }else{// ?????????
                Toast.makeText(this,"?????? ????????? ???????????????",Toast.LENGTH_SHORT).show()
            }

        }
    }

    override fun onDestroy() {
       // keyboardVisibilityUtils.detachKeyboardListeners()
        super.onDestroy()
    }



    /**?????????????????? ?????? ???????????? ??? ?????? ???????????? ??????  ??? ?????????(true)*/
    private fun checkPossibilityUpload(
        title: String, contents: String, place1: String, place2: String, place3: String,
        participantNum: Int, days: MutableList<Int>,category:Int
    ): Boolean {
        val place = mutableListOf<String>() //????????? ?????????
        if (onlyLetterCount(place1) != 0) place.add(place1.trim())
        if (onlyLetterCount(place2) != 0) place.add(place2.trim())
        if (onlyLetterCount(place3) != 0) place.add(place3.trim())
        return !(onlyLetterCount(title) == 0 ||
                onlyLetterCount(contents) == 0 ||
                place.isEmpty() ||
                days.isEmpty() ||
                participantNum == 0||
                category ==0)
    }


    private fun forFirebaseDataMapMaker(
        title: String, contents: String, place1: String, place2: String, place3: String,
        participantNum: Int, days: MutableList<Int>,category:Int
    ): MutableMap<String, Any> {
        val place = mutableListOf<String>() //????????? ?????????
        if (onlyLetterCount(place1) != 0) place.add(place1.trim())
        if (onlyLetterCount(place2) != 0) place.add(place2.trim())
        if (onlyLetterCount(place3) != 0) place.add(place3.trim())


        //????????? ?????????,?????????,????????? ???????????? : ?????????????????? ????????? ??????
        var placeString = ""
        placeString = if(place.size ==1){
            place[0]
        }else if(place.size ==2){
            place[0]+",${place[1]}"
        }else {
            place[0]+",${place[1]}"+",${place[2]}"
        }

        // ????????? ????????? ???????????? ??????????????? 1,3,5????????? ????????????
        val orderedDays = days.sorted()
        var daysString = ""
        if(days.size ==1){
            daysString = "${days[0]}"
        }else{
            for (i in 0 until days.size){
                if(i ==0){
                    daysString = "${orderedDays[i]}"
                }else{
                    daysString+=",${orderedDays[i]}"
                }
            }
        }



        val resultMap = mutableMapOf<String,Any>()
        resultMap["title"] = title
        resultMap["contents"] = contents
        resultMap["place"] = placeString
        resultMap["participantNum"] = participantNum
        resultMap["days"] = daysString
        resultMap["district"]=district


        val randomBackgroundGenerator = (1..8).random()
        resultMap["randomBackground"] = randomBackgroundGenerator

        return resultMap
    }


    private fun removeVacancy(contents: String): String {
        return contents.replace(" ", "")
    }

    private fun onlyLetterCount(contents: String): Int {
        return removeVacancy(contents).length
    }



}