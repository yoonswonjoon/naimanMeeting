package com.example.rodem.a0Common.dialog

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
import com.example.rodem.a0Common.a0Object.GlobalResponse
import com.example.rodem.databinding.A0DialogBasicBinding

class A0TextDialogBasic : DialogFragment() {

    interface DialogListener {
        fun fromShortageDialogRespond(
                respond: Boolean
        )
    }


    private fun sendResult(result: Boolean) {
        val intent = Intent()
        if (result) {
            if (responseListener != null) //activity 응답
            {
                responseListener?.fromShortageDialogRespond(respond = true)
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
                responseListener?.fromShortageDialogRespond(respond = false)
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

    private var responseListener: DialogListener? = null

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
        with(binding)
        {

            a0DialogBasicContents.text = "정말 작성하시겠습니까?"

            a0DialogBasicBtnOk.setOnClickListener {
                sendResult(true)
                //val intent = Intent(context, A7AMain::class.java)
                //intent.flags += Intent.FLAG_ACTIVITY_CLEAR_TOP
                //startActivity(intent)
                a0DialogBasicBtnOk.isClickable = false
                dismiss()
            }


            a0DialogBasicBtnNo.setOnClickListener {
                sendResult(false)
                a0DialogBasicBtnNo.isClickable = false
                dismiss()
            }
            dialog?.window?.setBackgroundDrawable(
                ColorDrawable(Color.TRANSPARENT))
        }
    }


    private var _binding: A0DialogBasicBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, )
    : View? {
        _binding = A0DialogBasicBinding.inflate(inflater, container, false)
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


        dialog?.window?.setLayout(dialogWidth,ActionBar.LayoutParams.WRAP_CONTENT)
        super.onResume()
    }


}