package com.diego.rappijob.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.diego.model.Movie
import com.diego.rappijob.R
import com.diego.rappijob.util.Utils

class MovieAdapter(
    private val onItemSelectedListener: OnItemSelectedListener<Movie>
) : RecyclerView.Adapter<MovieAdapter.MovieAdapterViewHolder>() {

    private val movies: MutableList<Movie> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapterViewHolder =
        MovieAdapterViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.information_item, parent, false))

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieAdapterViewHolder, position: Int) {
        val movie = movies[position]
        holder.posterImage?.let {
            Utils.loadImage(holder.itemView.context, movie.posterPath, it)
        }
        holder.titleMovie?.text = movie.title
        holder.yearMovie?.text = movie.releaseDate.subSequence(0, 4)

        holder.itemView.setOnClickListener {
            onItemSelectedListener.onItemSelected(movie)
        }
    }

    fun updateData(items: List<Movie>) {
        val diffCallback = MovieDiffCallback(movies, items)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        movies.clear()
        movies.addAll(items)
        diffResult.dispatchUpdatesTo(this)
        notifyDataSetChanged()
    }

    class MovieAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal val posterImage: ImageView? = itemView.findViewById(R.id.imageViewPoster)
        internal val titleMovie: TextView? = itemView.findViewById(R.id.textViewTitle)
        internal val yearMovie: TextView? = itemView.findViewById(R.id.textViewYear)
    }

    class MovieDiffCallback(
        private val oldList: List<Movie>,
        private val newList: List<Movie>
    ) : DiffUtil.Callback() {

        override fun getOldListSize() = oldList.size

        override fun getNewListSize() = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldList[oldItemPosition] == newList[newItemPosition]

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
                    && oldList[oldItemPosition].title == newList[newItemPosition].title
        }
    }
}