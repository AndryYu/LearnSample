package com.andryu.kotlin.learn

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.andryu.kotlin.base.entity.LearnEntity
import com.andryu.kotlin.third.R
import com.andryu.kotlin.third.ThirdPartyAdapter

class MainAdapter (private val dataList:MutableList<LearnEntity>): RecyclerView.Adapter<ThirdPartyAdapter.ViewHolder>(){

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mItemName: AppCompatTextView = itemView.findViewById(R.id.tv_third_content_name)
    }

    private var mListener: ((entity:LearnEntity) -> Unit?)? = null

    fun setOnItemClick(listener:(entity:LearnEntity) -> Unit){
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThirdPartyAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_third_party_content, parent, false)
        return ThirdPartyAdapter.ViewHolder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: ThirdPartyAdapter.ViewHolder, position: Int) {
        val entity = dataList[position]
        holder.run {
            mItemName.text = entity.name
            itemView.setOnClickListener {
                mListener?.invoke(entity)
            }
        }
    }
}