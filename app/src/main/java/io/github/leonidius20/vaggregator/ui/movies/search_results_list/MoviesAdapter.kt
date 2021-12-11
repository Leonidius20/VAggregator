package io.github.leonidius20.vaggregator.ui.movies.search_results_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.github.leonidius20.vaggregator.R
import io.github.leonidius20.vaggregator.data.providers.ThePirateBayMovie

class MoviesAdapter(private val movies: Array<ThePirateBayMovie>): RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val imageView = itemView.findViewById<ImageView>(R.id.movies_search_result_item_thumbnail)
        val titleTextView = itemView.findViewById<TextView>(R.id.movies_search_result_item_title)
        val descriptionTextView = itemView.findViewById<TextView>(R.id.movies_search_result_item_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.movies_serach_results_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.titleTextView.text = movie.name

        // TODO: better conversion to support GB, TB and so forth
        val sizeMegabytes = movie.size / 1048576F
        holder.descriptionTextView.text = "%.2f MB".format(sizeMegabytes)
    }

    override fun getItemCount() = movies.size

}