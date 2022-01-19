package com.jeanbernuy.citymapper.presentation.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.vipulasri.timelineview.TimelineView
import com.jeanbernuy.citymapper.R
import com.jeanbernuy.citymapper.core.BaseViewHolder
import com.jeanbernuy.citymapper.core.utils.VectorDrawableUtils
import com.jeanbernuy.citymapper.data.model.StopPointX
import com.jeanbernuy.citymapper.databinding.ItemTimelineBinding

class TimeLineAdapter(private val context: Context, private val stopPoint: ArrayList<StopPointX>) :
    RecyclerView.Adapter<TimeLineAdapter.TimeLineViewHolder>() {


    override fun getItemViewType(position: Int): Int {
        return TimelineView.getTimeLineViewType(position, itemCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeLineViewHolder {
        val itemBinding = ItemTimelineBinding.inflate(LayoutInflater.from(context), parent, false)
        return TimeLineViewHolder(itemBinding, viewType)
    }

    override fun onBindViewHolder(holder: TimeLineViewHolder, position: Int) {
        holder.bind(stopPoint[position], position)
    }

    override fun getItemCount() = stopPoint.size

    inner class TimeLineViewHolder(
        private val binding: ItemTimelineBinding,
        private val viewType: Int,
    ) :
        BaseViewHolder<StopPointX>(binding.root) {
        override fun bind(item: StopPointX, position: Int) = with(binding) {
            if (position == 0) timeline.marker = VectorDrawableUtils.getDrawable(context, R.drawable.ic_marker) else timeline.marker = VectorDrawableUtils.getDrawable(context, R.drawable.ic_marker_inactive)
            txtTimelineTitle.text = item.name
            timeline.initLine(viewType)
        }

    }

}