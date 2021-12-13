package io.github.leonidius20.vaggregator.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mancj.materialsearchbar.MaterialSearchBar
import io.github.leonidius20.vaggregator.R
import io.github.leonidius20.vaggregator.databinding.FragmentMoviesBinding
import io.github.leonidius20.vaggregator.ui.movie_details.MovieDetailsViewModel
import io.github.leonidius20.vaggregator.ui.movies.search_results_list.SearchResultsAdapter

class MoviesFragment : Fragment(), MaterialSearchBar.OnSearchActionListener {

    private val moviesViewModel: MoviesViewModel by navGraphViewModels(R.id.mobile_navigation)

    private val selectedMovieViewModel: MovieDetailsViewModel by activityViewModels()

    private var _binding: FragmentMoviesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.searchResultsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
        }

        moviesViewModel.movies.observe(viewLifecycleOwner) {
            binding.searchResultsRecyclerView.adapter =
                SearchResultsAdapter(it.toTypedArray()) { selectedMovie ->
                    selectedMovieViewModel.select(selectedMovie)
                    val action = MoviesFragmentDirections.actionMoviesToMovieDetails()
                    findNavController().navigate(action)
                }
        }

        binding.moviesSearchBar.setOnSearchActionListener(this)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onSearchStateChanged(enabled: Boolean) {

    }

    override fun onSearchConfirmed(text: CharSequence?) {
        moviesViewModel.loadMovies(text.toString())
    }

    override fun onButtonClicked(buttonCode: Int) {

    }

}