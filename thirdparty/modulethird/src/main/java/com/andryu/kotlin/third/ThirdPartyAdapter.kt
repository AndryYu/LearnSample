package com.andryu.kotlin.third

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.andryu.kotlin.base.entity.LearnEntity
import javax.inject.Inject
import javax.inject.Named

class ThirdPartyAdapter @Inject constructor(@Named("thirdList") private val thirdList:MutableList<LearnEntity>): RecyclerView.Adapter<ThirdPartyAdapter.ViewHolder>() {

    private var mListener: ((entity:LearnEntity) -> Unit?)? = null

    fun setOnItemClick(listener:(entity:LearnEntity) -> Unit){
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_third_party_content, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = thirdList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val entity = thirdList[position]
        holder.run {
            mItemName.text = entity.name
            itemView.setOnClickListener {
                mListener?.invoke(entity)
            }
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mItemName: AppCompatTextView = itemView.findViewById(R.id.tv_third_content_name)
    }
}