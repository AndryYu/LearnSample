package com.andryu.kotlin.learn

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.andryu.kotlin.base.entity.LearnEntity
import com.andryu.kotlin.third.R
import com.andryu.kotlin.third.ThirdPartyAdapter
import javax.inject.Inject
import javax.inject.Named

class MainAdapter @Inject constructor (@Named("categoryList") private val categoryList:MutableList<LearnEntity>): RecyclerView.Adapter<ThirdPartyAdapter.ViewHolder>(){

    private var mSelectPosition: Int = 0
    private var mListener: ((entity:LearnEntity) -> Unit?)? = null

    fun setOnItemClick(listener:(entity:LearnEntity) -> Unit){
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_home_content, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = categoryList.size

    override fun onBindViewHolder(holder: ThirdPartyAdapter.ViewHolder, position: Int) {
        val entity = categoryList[position]
        holder.run {
            mItemName.text = entity.name
            itemView.setOnClickListener {
                mListener?.invoke(entity)
                if (position != mSelectPosition){
                    notifyItemChanged(mSelectPosition);
                    mSelectPosition = position;
                }
                notifyItemChanged(position)
            }

            mItemName.isSelected = (position == mSelectPosition)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mItemName: AppCompatTextView = itemView.findViewById(R.id.tv_home_item)
    }
}