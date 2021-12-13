package io.github.leonidius20.vaggregator.ui.videos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mancj.materialsearchbar.MaterialSearchBar
import io.github.leonidius20.vaggregator.R
import io.github.leonidius20.vaggregator.data.videos.VideoCategory
import io.github.leonidius20.vaggregator.databinding.FragmentVideosBinding
import io.github.leonidius20.vaggregator.ui.movie_details.MovieDetailsViewModel
import io.github.leonidius20.vaggregator.ui.movies.search_results_list.SearchResultsAdapter

class VideosFragment : Fragment(), MaterialSearchBar.OnSearchActionListener, AdapterView.OnItemSelectedListener {

    private val videosViewModel: VideosViewModel by navGraphViewModels(R.id.mobile_navigation)
    private var _binding: FragmentVideosBinding? = null

    private val contentDetailsViewModel: MovieDetailsViewModel by activityViewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVideosBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.videosSearchBar.setOnSearchActionListener(this)

        binding.searchResultsRecyclerView.layoutManager = LinearLayoutManager(context)

        binding.videosCategorySelector.adapter = ArrayAdapter(context!!, android.R.layout.simple_spinner_item, VideoCategory.values())
        binding.videosCategorySelector.onItemSelectedListener = this

        videosViewModel.videos.observe(viewLifecycleOwner) {
            binding.searchResultsRecyclerView.adapter =
                SearchResultsAdapter(it.toTypedArray()) { video ->
                    contentDetailsViewModel.select(video)
                    findNavController().navigate(VideosFragmentDirections.actionVideosListToDetails())
                }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onSearchStateChanged(enabled: Boolean) {

    }

    override fun onSearchConfirmed(text: CharSequence?) {
        videosViewModel.loadVideos(text.toString(), videosViewModel.selectedCategory)
    }

    override fun onButtonClicked(buttonCode: Int) {

    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, postion: Int, p3: Long) {
        videosViewModel.selectedCategory = VideoCategory.values()[postion]
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }
}