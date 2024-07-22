package com.andryyu.kotlin.self

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.andryu.kotlin.base.entity.LearnEntity
import com.andryu.kotlin.self.R
import javax.inject.Inject
import javax.inject.Named

class SelfContainAdapter @Inject constructor(@Named("selfList") private val selfList:MutableList<LearnEntity>): RecyclerView.Adapter<SelfContainAdapter.ViewHolder>() {

    private var mListener: ((entity:LearnEntity) -> Unit?)? = null

    fun setOnItemClick(listener:(entity:LearnEntity) -> Unit){
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_self_content, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = selfList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val entity = selfList[position]
        holder.run {
            mItemName.text = entity.name
            itemView.setOnClickListener {
                mListener?.invoke(entity)
            }
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mItemName: AppCompatTextView = itemView.findViewById(R.id.tv_self_content)
    }
}