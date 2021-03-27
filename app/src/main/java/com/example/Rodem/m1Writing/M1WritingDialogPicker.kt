package com.example.Rodem.m1Writing

import android.app.ActionBar
import android.content.Context
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.example.Rodem.databinding.M1ParticipantPickerDialogBinding

class M1WritingDialogPicker() : DialogFragment() {

    interface DialogListener {
        fun sendPickerDialogRespond(
                dataInt: Int = 0,
                respond: Boolean
        )
    }


    private fun sendResult(result: Boolean, data: Int = 0) {

        if (result) {
            if (responseListener != null) //activity 응답
            {
                responseListener?.sendPickerDialogRespond(data,true)
            } else //fragment 응답
            {

            }
        } else {
            if (responseListener != null) //activity 응답
            {
                responseListener?.sendPickerDialogRespond(data, false)
            } else //fragment 응답
            {

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding)
        {

            m1PickerDialogPicker.wrapSelectorWheel = false
            val numberList = arrayOf("1", "2", "3", "4", "5", "6", "7")
            m1PickerDialogPicker.displayedValues = numberList
            m1PickerDialogPicker.minValue = 1
            m1PickerDialogPicker.maxValue = numberList.size
            m1PickerDialogTitle.text = "참여인원"
            // m3EditPickerDialogPicker.verticalScrollbarPosition = 5


            m1PickerDialogLeftBtn.text = "취소"
            m1PickerDialogRightBtn.text = "확인"


            m1PickerDialogRightBtn.setOnClickListener {
                sendResult(true,m1PickerDialogPicker.value )
                m1PickerDialogRightBtn.isClickable = false
                dismiss()
            }


            m1PickerDialogLeftBtn.setOnClickListener {
                sendResult(false)
                m1PickerDialogLeftBtn.isClickable = true

                dismiss()
            }
            dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }


//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        return inflater.inflate(R.layout.m3_edit_picker_dialog, container, false)
//    }

    private var _binding: M1ParticipantPickerDialogBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View? {
        _binding = M1ParticipantPickerDialogBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onResume() {
        val windowManager = context?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        val deviceWidth = size.x
        val dialogWidth = (deviceWidth * 0.8).toInt()


        dialog?.window?.setLayout(dialogWidth, ActionBar.LayoutParams.WRAP_CONTENT)
        super.onResume()
    }


}