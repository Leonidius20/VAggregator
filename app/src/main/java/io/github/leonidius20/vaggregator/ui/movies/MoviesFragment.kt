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
import com.google.android.material.snackbar.Snackbar
import com.mancj.materialsearchbar.MaterialSearchBar
import io.github.leonidius20.vaggregator.R
import io.github.leonidius20.vaggregator.data.Status
import io.github.leonidius20.vaggregator.databinding.FragmentMoviesBinding
import io.github.leonidius20.vaggregator.ui.base.list.SearchResultsAdapter
import io.github.leonidius20.vaggregator.ui.hideKeyboard
import io.github.leonidius20.vaggregator.ui.isNetworkConnected
import io.github.leonidius20.vaggregator.ui.movie_details.SelectedContentSharedViewModel

class MoviesFragment : Fragment(), MaterialSearchBar.OnSearchActionListener {

    private val moviesViewModel: MoviesViewModel by navGraphViewModels(R.id.mobile_navigation)

    private val selectedMovieViewModel: SelectedContentSharedViewModel by activityViewModels()

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

        binding.moviesSearchResultsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
        }

        moviesViewModel.movies.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.moviesProgress.visibility = View.GONE

                    binding.moviesSearchResultsRecyclerView.apply {
                        visibility = View.VISIBLE
                        adapter =
                            SearchResultsAdapter(it.data!!.toTypedArray()) { selectedMovie ->
                                selectedMovieViewModel.select(selectedMovie)
                                val action = MoviesFragmentDirections
                                    .actionMoviesToMovieDetails()
                                findNavController().navigate(action)
                            }
                    }

                    if (it.message != null && !moviesViewModel.errorShown.value!!) {
                        Snackbar.make(activity!!.window.decorView,
                            it.message.toString(), Snackbar.LENGTH_LONG).show()
                        moviesViewModel.errorShown.value = true
                    }

                    binding.moviesSearchNoResults.visibility = if (it.data!!.isEmpty()) View.VISIBLE else View.GONE
                }
                Status.ERROR -> {
                    binding.moviesProgress.visibility = View.GONE
                    binding.moviesSearchNoResults.visibility = View.GONE
                    binding.moviesSearchResultsRecyclerView.visibility = View.GONE
                    Snackbar.make(activity!!.window.decorView,  it.message.toString(), Snackbar.LENGTH_LONG).show()
                }
                else -> {
                    binding.moviesProgress.visibility = View.VISIBLE
                    binding.moviesSearchNoResults.visibility = View.GONE
                    binding.moviesSearchResultsRecyclerView.visibility = View.GONE
                }
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
        if (text.isNullOrBlank()) return
        hideKeyboard()
        if (!isNetworkConnected()) {
            Snackbar.make(activity!!.window.decorView, R.string.no_internet, Snackbar.LENGTH_LONG).show()
        } else {
            moviesViewModel.loadMovies(text.toString())
        }
    }

    override fun onButtonClicked(buttonCode: Int) {

    }

}