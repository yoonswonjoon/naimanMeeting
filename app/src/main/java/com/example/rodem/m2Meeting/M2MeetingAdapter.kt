package com.example.rodem.m2Meeting

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rodem.R
import kotlinx.android.synthetic.main.m2_meeting_item_sample.view.*
import vlm.naimanmaster.a1Functions.a13DateControl.timeLapseComplete
import vlm.naimanmaster.a1Functions.a13DateControl.timestamptoDate

class M2MeetingAdapter(private val itemList :MutableList<MutableMap<String, Any>>) :
    RecyclerView.Adapter<M2MeetingAdapter.RepositoryHolder>(){



    class RepositoryHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(
        R.layout.m2_meeting_item_sample, parent, false))

    private var listener: ItemClickListener? = null

    fun setItemClickListener(listener: ItemClickListener?) {
        this.listener = listener
    }
    interface ItemClickListener {
        fun onItemClick( detail_data :MutableMap<String,Any>)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryHolder =
        RepositoryHolder(parent)



    override fun onBindViewHolder(holder: RepositoryHolder, position: Int) {

        val itemData = itemList[position]
        with(holder.itemView){
            m2_meeting_item_sample_title.text = itemData["title"].toString()
            m2_meeting_item_sample_want_place.text = "# ${itemData["place"].toString().replace(",","  ")}"

            val dayList = itemData["days"].toString().split(",")
            m2_meeting_item_sample_day.text = dayReturner(dayList)
            m2_meeting_item_sample_participant_number.text = itemData["participantNum"].toString()

            m2_meeting_item_sample_upload_date.text = timeLapseComplete(timestamptoDate(itemData["date"]))


            m2_meeting_item_sample_card.setOnClickListener{
                listener?.onItemClick(itemData)
            }


        }
    }


    override fun getItemId(position: Int): Long {
        return position.toLong()
    }



    override fun getItemCount(): Int {
        return itemList.size
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