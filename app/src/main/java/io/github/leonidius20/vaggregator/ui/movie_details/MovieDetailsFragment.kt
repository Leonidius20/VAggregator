package io.github.leonidius20.vaggregator.ui.movie_details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.squareup.picasso.Picasso
import io.github.leonidius20.vaggregator.R
import io.github.leonidius20.vaggregator.databinding.FragmentMovieDetailsBinding

class MovieDetailsFragment: Fragment() {

    private val viewModel: SelectedContentSharedViewModel by activityViewModels()
    private var _binding: FragmentMovieDetailsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)

        viewModel.selectedMovie.observe(viewLifecycleOwner) { movie ->
            with(binding) {
                movieDetailsTitle.text = movie.name
                movieDetailsSize.text = Html.fromHtml(movie.description)
                movieDetailsDate.text = movie.provider

                openButton.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(movie.link))
                    startActivity(intent)
                }

                if (movie.bigThumbnailUrl != null) {
                    Picasso.get()
                        .load(movie.bigThumbnailUrl)
                        .placeholder(R.drawable.placeholder_thumbnail)
                        .fit().centerInside()
                        .into(movieDetailsThumbnail)
                } else {
                    movieDetailsThumbnail.setImageResource(R.drawable.placeholder_thumbnail)
                }

            }
        }

        val onSaveToLibrary = { _: View -> viewModel.saveToLibrary() }
        val onRemoveFromLibrary = { _: View -> viewModel.removeFromLibrary() }

        viewModel.isContentSavedToLibrary.observe(viewLifecycleOwner) {
            binding.movieDetailsSaveButton.apply {
                if (it) {
                    setImageResource(R.drawable.baseline_library_add_check_24)
                    setOnClickListener(onRemoveFromLibrary)
                } else {
                    setImageResource(R.drawable.baseline_library_add_24)
                    setOnClickListener(onSaveToLibrary)
                }
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}