package com.jeanbernuy.citymapper.presentation.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jeanbernuy.citymapper.core.BaseViewHolder
import com.jeanbernuy.citymapper.data.model.ArrivalsItem
import com.jeanbernuy.citymapper.databinding.ItemArrivalTimeBinding

class ArrivalTimesAdapter(
    private val context: Context,
    private val arrivals: ArrayList<ArrivalsItem>
) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding =
            ItemArrivalTimeBinding.inflate(LayoutInflater.from(context), parent, false)
        return ArrivalTimeViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is ArrivalTimeViewHolder -> holder.bind(arrivals[position],position)
        }
    }

    override fun getItemCount(): Int {
        return arrivals.size
    }

    inner class ArrivalTimeViewHolder(private val binding: ItemArrivalTimeBinding) :
        BaseViewHolder<ArrivalsItem>(binding.root) {
        override fun bind(item: ArrivalsItem, position: Int) = with(binding) {
            txtLineName.text = "Central"
            txtExpectedArrival.text = "20:30"
            txtDirection.text = "inbound"
        }

    }


}