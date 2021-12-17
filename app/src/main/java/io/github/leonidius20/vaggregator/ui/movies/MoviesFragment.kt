package io.github.leonidius20.vaggregator.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.navGraphViewModels
import io.github.leonidius20.vaggregator.R
import io.github.leonidius20.vaggregator.databinding.FragmentMoviesBinding
import io.github.leonidius20.vaggregator.ui.base.search.BaseSearchFragment

class MoviesFragment : BaseSearchFragment<MoviesViewModel>() {

    private var _binding: FragmentMoviesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun setupView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun createViewModel(): Lazy<MoviesViewModel> {
        return navGraphViewModels(R.id.mobile_navigation)
    }

    override fun createToContentActionDetails(): NavDirections {
        return MoviesFragmentDirections.actionMoviesToMovieDetails()
    }

}