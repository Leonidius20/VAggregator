package io.github.leonidius20.vaggregator.ui.videos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.navigation.NavDirections
import androidx.navigation.navGraphViewModels
import io.github.leonidius20.vaggregator.R
import io.github.leonidius20.vaggregator.data.videos.VideoCategory
import io.github.leonidius20.vaggregator.databinding.FragmentVideosBinding
import io.github.leonidius20.vaggregator.ui.base.search.BaseSearchFragment

class VideosFragment : BaseSearchFragment<VideosViewModel>(), AdapterView.OnItemSelectedListener {

    private var _binding: FragmentVideosBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun setupView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentVideosBinding.inflate(inflater, container, false)
        binding.videosCategorySelector.apply {
            adapter = ArrayAdapter(context!!,
                android.R.layout.simple_spinner_item, VideoCategory.values())
            onItemSelectedListener = this@VideosFragment
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, postion: Int, p3: Long) {
        viewModel.selectedCategory = VideoCategory.values()[postion]
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {}

    override fun createViewModel(): Lazy<VideosViewModel> {
        return navGraphViewModels(R.id.mobile_navigation)
    }

    override fun createToContentActionDetails(): NavDirections {
        return VideosFragmentDirections.actionVideosListToDetails()
    }

}