package io.github.leonidius20.vaggregator.ui.base.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import com.mancj.materialsearchbar.MaterialSearchBar
import io.github.leonidius20.vaggregator.R
import io.github.leonidius20.vaggregator.databinding.BaseSearchLayoutBinding
import io.github.leonidius20.vaggregator.ui.base.list.BaseListFragment
import io.github.leonidius20.vaggregator.ui.hideKeyboard
import io.github.leonidius20.vaggregator.ui.isNetworkConnected

abstract class BaseSearchFragment<VM: BaseSearchViewModel>: BaseListFragment<VM>(), MaterialSearchBar.OnSearchActionListener {

    private var _searchBinding: BaseSearchLayoutBinding? = null

    val searchBinding get() = _searchBinding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = super.onCreateView(inflater, container, savedInstanceState)!!
        _searchBinding = BaseSearchLayoutBinding.bind(root)
        searchBinding.searchBar.setOnSearchActionListener(this)
        if (!isNetworkConnected()) {
            Snackbar.make(activity!!.window.decorView,
                R.string.no_internet, Snackbar.LENGTH_LONG).show()
        }
        return root
    }

    override fun onSearchConfirmed(text: CharSequence?) {
        if (text.isNullOrBlank()) return
        hideKeyboard()
        if (!isNetworkConnected()) {
            Snackbar.make(activity!!.window.decorView, R.string.no_internet, Snackbar.LENGTH_LONG).show()
        } else {
            viewModel.searchQuery = text.toString()
            viewModel.loadData()
        }
    }

    override fun onSearchStateChanged(enabled: Boolean) {}

    override fun onButtonClicked(buttonCode: Int) {}

    override fun onDestroyView() {
        super.onDestroyView()
        _searchBinding = null
    }

}