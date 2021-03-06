package io.github.leonidius20.vaggregator.ui.base.list

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import io.github.leonidius20.vaggregator.R
import io.github.leonidius20.vaggregator.data.PieceOfContent

class SearchResultsAdapter(private val movies: Array<PieceOfContent>, private val onClick: (PieceOfContent) -> Unit): RecyclerView.Adapter<SearchResultsAdapter.ViewHolder>() {

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
        // https://square.github.io/picasso/

        if (movie.thumbnailUrl != null) {
            Picasso.get()
                .load(movie.thumbnailUrl)
                .fit()
                .centerCrop()
                .into(holder.imageView)
        } else {
            holder.imageView.setImageResource(R.drawable.placeholder_thumbnail)
        }

        holder.descriptionTextView.text = Html.fromHtml(movie.description)
        holder.itemView.setOnClickListener { onClick(movie) }
    }

    override fun getItemCount() = movies.size

}