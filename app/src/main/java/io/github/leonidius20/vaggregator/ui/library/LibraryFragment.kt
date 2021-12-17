package io.github.leonidius20.vaggregator.ui.library

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.navGraphViewModels
import io.github.leonidius20.vaggregator.R
import io.github.leonidius20.vaggregator.databinding.FragmentLibraryBinding
import io.github.leonidius20.vaggregator.ui.base.list.BaseListFragment

class LibraryFragment : BaseListFragment<LibraryViewModel>() {

    private var _binding: FragmentLibraryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun setupView(inflater: LayoutInflater,
                           container: ViewGroup?,
                           savedInstanceState: Bundle?): View {
        _binding = FragmentLibraryBinding.inflate(inflater, container, false)
        viewModel.loadData()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun createViewModel(): Lazy<LibraryViewModel> {
        return navGraphViewModels(R.id.mobile_navigation)
    }

    override fun createToContentActionDetails(): NavDirections {
        return LibraryFragmentDirections.actionLibraryToContentDetails()
    }
}