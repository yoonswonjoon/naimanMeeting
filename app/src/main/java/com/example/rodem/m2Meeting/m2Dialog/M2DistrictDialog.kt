package com.example.rodem.m2Meeting.m2Dialog

import android.annotation.SuppressLint
import android.app.ActionBar
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.example.rodem.a0Common.a0Object.GlobalDataSet.districtDataSet
import com.example.rodem.a0Common.a0Object.GlobalResponse
import com.example.rodem.a0Common.dialog.A0TextDialogBasic
import com.example.rodem.databinding.A0DialogBasicBinding
import com.example.rodem.databinding.M2MeetingOptionDistrictDialogBinding

class M2DistrictDialog : DialogFragment() {
    interface DialogListener {
        fun m2DistrictRespond(
                respond: Boolean,
                district:Int
        )
    }

    private var responseListener: DialogListener? = null

    private fun sendResult(result: Boolean,district : Int) {
        val intent = Intent()
        if (result) {
            if (responseListener != null) //activity 응답
            {
                responseListener?.m2DistrictRespond(respond = true,district)
            } else //fragment 응답
            {
                intent.putExtra("isProcessOk", true) // 1이면 포인트 픽스, 0이면 픽스 취소

                targetFragment!!.onActivityResult(
                        GlobalResponse.F_Basic_Dialog_Request,
                        GlobalResponse.F_Basic_Dialog_Response, intent
                )
            }
        } else {
            if (responseListener != null) //activity 응답
            {
                responseListener?.m2DistrictRespond(respond = false,district)
            } else //fragment 응답
            {
                intent.putExtra("isProcessOk", false) // 1이면 포인트 픽스, 0이면 픽스 취소
                targetFragment!!.onActivityResult(
                        GlobalResponse.F_Basic_Dialog_Request,
                        GlobalResponse.F_Basic_Dialog_Response, intent
                )
            }
        }
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            responseListener = context as DialogListener
        } catch (e: Exception) {

        }
    }


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var districtInt =0
        with(binding)
        {



            m2MeetingOptionDistrictBtn0.setOnClickListener {
                districtInt = 0
                m2MeetingOptionDistrictText.text = districtDataSet[districtInt]
            }

            m2MeetingOptionDistrictBtn1.setOnClickListener {
                districtInt = 1
                m2MeetingOptionDistrictText.text = districtDataSet[districtInt]
            }

            m2MeetingOptionDistrictBtn2.setOnClickListener {
                districtInt = 2
                m2MeetingOptionDistrictText.text = districtDataSet[districtInt]
            }

            m2MeetingOptionDistrictBtn3.setOnClickListener {
                districtInt = 3
                m2MeetingOptionDistrictText.text = districtDataSet[districtInt]
            }

            m2MeetingOptionDistrictBtn4.setOnClickListener {
                districtInt = 4
                m2MeetingOptionDistrictText.text = districtDataSet[districtInt]
            }

            m2MeetingOptionDistrictBtn5.setOnClickListener {
                districtInt = 5
                m2MeetingOptionDistrictText.text = districtDataSet[districtInt]
            }

            m2MeetingOptionDistrictBtn6.setOnClickListener {
                districtInt = 6
                m2MeetingOptionDistrictText.text = districtDataSet[districtInt]
            }

            m2MeetingOptionDistrictBtn7.setOnClickListener {
                districtInt = 7
                m2MeetingOptionDistrictText.text = districtDataSet[districtInt]
            }

            m2MeetingOptionDistrictBtn8.setOnClickListener {
                districtInt = 8
                m2MeetingOptionDistrictText.text = districtDataSet[districtInt]
            }

            m2MeetingOptionDistrictBtn9.setOnClickListener {
                districtInt = 9
                m2MeetingOptionDistrictText.text = districtDataSet[districtInt]
            }

            m2MeetingOptionDistrictBtn10.setOnClickListener {
                districtInt = 10
                m2MeetingOptionDistrictText.text = districtDataSet[districtInt]
            }

            m2MeetingOptionDistrictBtn11.setOnClickListener {
                districtInt = 11
                m2MeetingOptionDistrictText.text = districtDataSet[districtInt]
            }

            m2MeetingOptionDistrictBtn12.setOnClickListener {
                districtInt = 12
                m2MeetingOptionDistrictText.text = districtDataSet[districtInt]
            }

            m2MeetingOptionDistrictBtn13.setOnClickListener {
                districtInt = 13
                m2MeetingOptionDistrictText.text = districtDataSet[districtInt]
            }

            m2MeetingOptionDistrictBtn14.setOnClickListener {
                districtInt = 14
                m2MeetingOptionDistrictText.text = districtDataSet[districtInt]
            }

            m2MeetingOptionDistrictBtn15.setOnClickListener {
                districtInt = 15
                m2MeetingOptionDistrictText.text = districtDataSet[districtInt]
            }

            m2MeetingOptionDistrictBtn16.setOnClickListener {
                districtInt = 16
                m2MeetingOptionDistrictText.text = districtDataSet[districtInt]
            }

            m2MeetingOptionDistrictBtn17.setOnClickListener {
                districtInt = 17
                m2MeetingOptionDistrictText.text = districtDataSet[districtInt]
            }




            m2MeetingOptionDistrictBtnOk.setOnClickListener {

                sendResult(true,districtInt)
                //val intent = Intent(context, A7AMain::class.java)
                //intent.flags += Intent.FLAG_ACTIVITY_CLEAR_TOP
                //startActivity(intent)
                m2MeetingOptionDistrictBtnOk.isClickable = false
                dismiss()
            }


            m2MeetingOptionDistrictBtnNo.setOnClickListener {
                sendResult(false,0)
                m2MeetingOptionDistrictBtnNo.isClickable = false
                dismiss()
            }
            dialog?.window?.setBackgroundDrawable(
                    ColorDrawable(Color.TRANSPARENT))
        }
    }





    private var _binding: M2MeetingOptionDistrictDialogBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, )
            : View? {
        _binding = M2MeetingOptionDistrictDialogBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    /**다이얼 로그 크기 결정*/
    override fun onResume() {
        val windowManager = context?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        val deviceWidth = size.x
        val dialogWidth = (deviceWidth*0.8).toInt()


        dialog?.window?.setLayout(dialogWidth, ActionBar.LayoutParams.WRAP_CONTENT)
        super.onResume()
    }

}