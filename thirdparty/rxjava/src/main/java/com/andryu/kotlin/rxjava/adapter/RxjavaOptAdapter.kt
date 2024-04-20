package com.andryu.kotlin.rxjava.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.andryu.kotlin.rxjava.R
import com.andryu.kotlin.rxjava.data.RxjavaOptEntity

class RxjavaOptAdapter(private val dataList:MutableList<RxjavaOptEntity>) :
    RecyclerView.Adapter<RxjavaOptAdapter.RxjavaOptViewHolder>() {
    private var mListener: ((RxjavaOptEntity) -> Unit?)? = null

    fun setOnItemClick(listener: ((RxjavaOptEntity) -> Unit)) {
        mListener = listener
    }
    class RxjavaOptViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        val tvName:AppCompatTextView = itemView.findViewById(R.id.tv_opt_type)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RxjavaOptViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_rxjava_opt_type, parent, false)
        return RxjavaOptViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: RxjavaOptViewHolder, position: Int) {
        val entity = dataList[position]
        holder.run {
            tvName.text = entity.name
            itemView.setOnClickListener {
                mListener?.invoke(entity)
            }
        }
    }

}