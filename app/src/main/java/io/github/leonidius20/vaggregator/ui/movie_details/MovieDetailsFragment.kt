package io.github.leonidius20.vaggregator.ui.movie_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.navigation.navGraphViewModels
import io.github.leonidius20.vaggregator.R
import io.github.leonidius20.vaggregator.databinding.FragmentMovieDetailsBinding
import java.text.SimpleDateFormat
import java.util.*

class MovieDetailsFragment: Fragment() {

    private val viewModel: MovieDetailsViewModel by navGraphViewModels(R.id.mobile_navigation)
    private var _binding: FragmentMovieDetailsBinding? = null

    private val args: MovieDetailsFragmentArgs by navArgs()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)

        with(binding) {
            with(args.movie) {
                movieDetailsTitle.text = name
                movieDetailsSize.text = sizeString
                movieDetailsDate.text = SimpleDateFormat.getDateInstance().format(Date(args.movie.added * 1000))

            }
        }




        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}