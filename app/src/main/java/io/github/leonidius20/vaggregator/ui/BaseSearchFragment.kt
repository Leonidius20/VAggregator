package io.github.leonidius20.vaggregator.ui

import com.mancj.materialsearchbar.MaterialSearchBar

interface BaseSearchFragment: MaterialSearchBar.OnSearchActionListener {

    override fun onSearchStateChanged(enabled: Boolean) {}

    override fun onSearchConfirmed(text: CharSequence?) {

    }

    override fun onButtonClicked(buttonCode: Int) {}

}