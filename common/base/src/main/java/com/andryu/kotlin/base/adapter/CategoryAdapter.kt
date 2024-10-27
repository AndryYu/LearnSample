package com.andryu.kotlin.base.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.andryu.kotlin.base.R
import com.andryu.kotlin.base.entity.LearnEntity
import javax.inject.Inject
import javax.inject.Named

class NdkAdapter @Inject constructor(@Named("ndkList") private val ndkList:MutableList<LearnEntity>): RecyclerView.Adapter<NdkAdapter.ViewHolder>() {
class CategoryAdapter(private val dataList:MutableList<LearnEntity>): RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private var mListener: ((entity:LearnEntity) -> Unit?)? = null

    fun setOnItemClick(listener:(entity:LearnEntity) -> Unit){
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category_content, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val entity = dataList[position]
        holder.run {
            mItemName.text = entity.name
            itemView.setOnClickListener {
                mListener?.invoke(entity)
            }
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mItemName: AppCompatTextView = itemView.findViewById(R.id.tv_category_content_name)
    }
}