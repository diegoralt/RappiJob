package com.diego.rappijob.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.diego.model.Series
import com.diego.rappijob.R
import com.diego.rappijob.util.Utils

class SeriesAdapter(private val onItemSelectedListener: OnItemSelectedListener<Series>) :
    RecyclerView.Adapter<SeriesAdapter.SeriesAdapterViewHolder>() {

    private val series: MutableList<Series> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesAdapterViewHolder =
        SeriesAdapterViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.information_item, parent, false))

    override fun getItemCount(): Int = series.size

    override fun onBindViewHolder(holder: SeriesAdapterViewHolder, position: Int) {
        val item = series[position]
        holder.posterImage?.let {
            Utils.loadImage(holder.itemView.context, item.posterPath, it)
        }
        holder.nameSeries?.text = item.name
        holder.yearSeries?.text = item.firstAirDate.subSequence(0, 4)

        holder.itemView.setOnClickListener {
            onItemSelectedListener.onItemSelected(item)
        }
    }

    fun updateData(items: List<Series>) {
        val diffCallback = SeriesDiffCallback(series, items)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        series.clear()
        series.addAll(items)
        diffResult.dispatchUpdatesTo(this)
        notifyDataSetChanged()
    }

    class SeriesAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal val posterImage: ImageView? = itemView.findViewById(R.id.imageViewPoster)
        internal val nameSeries: TextView? = itemView.findViewById(R.id.textViewTitle)
        internal val yearSeries: TextView? = itemView.findViewById(R.id.textViewYear)
    }

    class SeriesDiffCallback(
        private val oldList: List<Series>,
        private val newList: List<Series>
    ) : DiffUtil.Callback() {

        override fun getOldListSize() = oldList.size

        override fun getNewListSize() = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldList[oldItemPosition] == newList[newItemPosition]

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
                    && oldList[oldItemPosition].name == newList[newItemPosition].name
        }
    }
}