package io.github.leonidius20.vaggregator.ui.movie_details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import io.github.leonidius20.vaggregator.databinding.FragmentMovieDetailsBinding

class MovieDetailsFragment: Fragment() {

    private val viewModel: MovieDetailsViewModel by activityViewModels()
    private var _binding: FragmentMovieDetailsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)

        viewModel.selectedMovie.observe(viewLifecycleOwner) { movie ->
            with(binding) {
                movieDetailsTitle.text = movie.name
                movieDetailsSize.text = movie.sizeString
                movieDetailsDate.text = movie.provider

                openButton.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(movie.link))
                    startActivity(intent)
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