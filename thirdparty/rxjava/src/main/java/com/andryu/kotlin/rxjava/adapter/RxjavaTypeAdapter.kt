package com.andryu.kotlin.rxjava.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.andryu.kotlin.rxjava.R
import com.andryu.kotlin.rxjava.data.RxjavaTypeEntity

class RxjavaTypeAdapter(private val dataList: MutableList<RxjavaTypeEntity>) :
    RecyclerView.Adapter<RxjavaTypeAdapter.RxjavaTypeViewHolder>() {
    private var mListener: ((RxjavaTypeEntity) -> Unit?)? = null

    fun setOnItemClick(listener: ((RxjavaTypeEntity) -> Unit)) {
        mListener = listener
    }

    class RxjavaTypeViewHolder(itemVIew: View) : RecyclerView.ViewHolder(itemVIew) {
        val tvName: AppCompatTextView = itemView.findViewById(R.id.tv_opt_type)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RxjavaTypeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_rxjava_opt_type, parent, false)
        return RxjavaTypeViewHolder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: RxjavaTypeViewHolder, position: Int) {
        val entity = dataList[position]
        holder.run {
            tvName.text = entity.name
            itemView.setOnClickListener {
                mListener?.invoke(entity)
            }
        }
    }
}