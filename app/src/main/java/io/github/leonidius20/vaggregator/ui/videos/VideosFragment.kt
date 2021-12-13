package io.github.leonidius20.vaggregator.ui.videos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mancj.materialsearchbar.MaterialSearchBar
import io.github.leonidius20.vaggregator.databinding.FragmentVideosBinding
import io.github.leonidius20.vaggregator.ui.movies.search_results_list.SearchResultsAdapter

class VideosFragment : Fragment(), MaterialSearchBar.OnSearchActionListener {

    private lateinit var videosViewModel: VideosViewModel
    private var _binding: FragmentVideosBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        videosViewModel =
            ViewModelProvider(this).get(VideosViewModel::class.java)

        _binding = FragmentVideosBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.videosSearchBar.setOnSearchActionListener(this)

        binding.searchResultsRecyclerView.layoutManager = LinearLayoutManager(context)

        videosViewModel.videos.observe(viewLifecycleOwner) {
            binding.searchResultsRecyclerView.adapter =
                SearchResultsAdapter(it.toTypedArray()) {
                    // TODO
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
        videosViewModel.loadVideos(text.toString())
    }

    override fun onButtonClicked(buttonCode: Int) {

    }
}