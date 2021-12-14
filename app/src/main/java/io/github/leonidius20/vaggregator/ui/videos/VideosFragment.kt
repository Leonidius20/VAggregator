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
import com.google.android.material.snackbar.Snackbar
import com.mancj.materialsearchbar.MaterialSearchBar
import io.github.leonidius20.vaggregator.R
import io.github.leonidius20.vaggregator.data.Status
import io.github.leonidius20.vaggregator.data.videos.VideoCategory
import io.github.leonidius20.vaggregator.databinding.FragmentVideosBinding
import io.github.leonidius20.vaggregator.ui.hideKeyboard
import io.github.leonidius20.vaggregator.ui.isNetworkConnected
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

        if (!isNetworkConnected()) {
            Snackbar.make(activity!!.window.decorView, R.string.no_internet, Snackbar.LENGTH_LONG).show()
            // Toast.makeText(context, "No internet", Toast.LENGTH_SHORT).show()
        }

        videosViewModel.videos.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.searchResultsRecyclerView.apply {
                        visibility = View.VISIBLE
                        adapter =
                            SearchResultsAdapter(it.data!!.toTypedArray()) { video ->
                                contentDetailsViewModel.select(video)
                                findNavController().navigate(VideosFragmentDirections.actionVideosListToDetails())
                            }
                    }
                    binding.videosProgress.visibility = View.GONE
                }
                Status.ERROR -> {
                    binding.videosProgress.visibility = View.GONE
                    binding.searchResultsRecyclerView.visibility = View.GONE
                    Snackbar.make(activity!!.window.decorView,  it.message.toString(), Snackbar.LENGTH_LONG).show()
                }
                else -> {
                    binding.videosProgress.visibility = View.VISIBLE
                    binding.searchResultsRecyclerView.visibility = View.GONE
                }
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
        if (text.isNullOrBlank()) return
        hideKeyboard()
        if (!isNetworkConnected()) {
            Snackbar.make(activity!!.window.decorView, R.string.no_internet, Snackbar.LENGTH_LONG).show()
        } else {
            videosViewModel.loadVideos(text.toString(), videosViewModel.selectedCategory)
        }
    }

    override fun onButtonClicked(buttonCode: Int) {

    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, postion: Int, p3: Long) {
        videosViewModel.selectedCategory = VideoCategory.values()[postion]
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

}