package io.github.leonidius20.vaggregator.ui.library

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import io.github.leonidius20.vaggregator.R
import io.github.leonidius20.vaggregator.databinding.FragmentLibraryBinding
import io.github.leonidius20.vaggregator.ui.movie_details.SelectedContentSharedViewModel
import io.github.leonidius20.vaggregator.ui.movies.search_results_list.SearchResultsAdapter

class LibraryFragment : Fragment() {

    private val libraryViewModel: LibraryViewModel by navGraphViewModels(R.id.mobile_navigation)
    private var _binding: FragmentLibraryBinding? = null

    private val selectedMovieViewModel: SelectedContentSharedViewModel by activityViewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLibraryBinding.inflate(inflater, container, false)

        binding.libraryContentRecyclerView.layoutManager = LinearLayoutManager(context)

        libraryViewModel.data.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                binding.libraryNoSavedContent.visibility = View.VISIBLE
                binding.libraryContentRecyclerView.visibility = View.GONE
            } else {
                binding.libraryNoSavedContent.visibility = View.GONE

                binding.libraryContentRecyclerView.apply {
                    visibility = View.VISIBLE
                    adapter =
                        SearchResultsAdapter(it!!.toTypedArray()) { selectedMovie ->
                            selectedMovieViewModel.select(selectedMovie)
                            val action = LibraryFragmentDirections.actionLibraryToContentDetails()
                            findNavController().navigate(action)
                        }
                }
            }
        }

        libraryViewModel.loadData()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}