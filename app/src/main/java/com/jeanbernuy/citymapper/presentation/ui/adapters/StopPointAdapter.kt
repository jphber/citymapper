package com.jeanbernuy.citymapper.presentation.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jeanbernuy.citymapper.core.BaseViewHolder
import com.jeanbernuy.citymapper.data.model.StopPointItem
import com.jeanbernuy.citymapper.databinding.ItemStopPointBinding

class StopPointAdapter(
    private val context: Context,
    private val stopPoints: ArrayList<StopPointItem>,
    private val itemClickListener: OnStopPointClickListener

) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnStopPointClickListener {
        fun onStopPointSelected(item: StopPointItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding = ItemStopPointBinding.inflate(LayoutInflater.from(context), parent, false)
        return StopPointViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is StopPointViewHolder -> holder.bind(stopPoints[position], position)
        }
    }

    override fun getItemCount(): Int {
        return stopPoints.size
    }

    inner class StopPointViewHolder(private val binding: ItemStopPointBinding) :
        BaseViewHolder<StopPointItem>(binding.root) {
        override fun bind(item: StopPointItem, position: Int) = with(binding) {
            txtStopPointName.text = item.commonName
            binding.root.setOnClickListener {
                itemClickListener.onStopPointSelected(item)
            }
        }

    }
}