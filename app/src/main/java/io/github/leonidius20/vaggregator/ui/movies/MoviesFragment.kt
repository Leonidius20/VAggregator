package io.github.leonidius20.vaggregator.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mancj.materialsearchbar.MaterialSearchBar
import io.github.leonidius20.vaggregator.R
import io.github.leonidius20.vaggregator.data.providers.ThePirateBayMovie
import io.github.leonidius20.vaggregator.databinding.FragmentMoviesBinding
import io.github.leonidius20.vaggregator.ui.movies.search_results_list.MoviesAdapter

class MoviesFragment : Fragment(), MaterialSearchBar.OnSearchActionListener {

    private val moviesViewModel: MoviesViewModel by navGraphViewModels(R.id.mobile_navigation)
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
            adapter = MoviesAdapter(arrayOf(ThePirateBayMovie("", "Test Movie", "", 0, 0, 0, 0, "", 0, "", 200, "")))
        }

        moviesViewModel.movies.observe(viewLifecycleOwner) {
            binding.searchResultsRecyclerView.adapter = MoviesAdapter(it.toTypedArray())
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