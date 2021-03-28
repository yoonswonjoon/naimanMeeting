package com.example.rodem.m2Meetting

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rodem.R
import kotlinx.android.synthetic.main.m2_meeting_item_sample.view.*

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
        fun onItemClick( detail_data :MutableMap<String,Any>,position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryHolder =
        RepositoryHolder(parent)



    override fun onBindViewHolder(holder: RepositoryHolder, position: Int) {
        val itemData = itemList[position]
        with(holder.itemView){
            m2_meeting_item_sample_title.text = itemData["title"].toString()

        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }


}