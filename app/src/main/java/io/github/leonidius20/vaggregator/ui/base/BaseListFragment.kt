package io.github.leonidius20.vaggregator.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import io.github.leonidius20.vaggregator.data.Status
import io.github.leonidius20.vaggregator.databinding.BaseListBinding
import io.github.leonidius20.vaggregator.ui.movie_details.SelectedContentSharedViewModel
import io.github.leonidius20.vaggregator.ui.movies.search_results_list.SearchResultsAdapter

abstract class BaseListFragment<VM: BaseListViewModel>: Fragment() {

    private var _listBinding: BaseListBinding? = null

    val listBinding get() = _listBinding!!

    val viewModel: VM by createViewModel()

    private val selectedContentViewModel: SelectedContentSharedViewModel by activityViewModels()

    private val toContentDetailsAction: NavDirections by lazy { createToContentActionDetails() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = setupView(inflater, container, savedInstanceState)
        _listBinding = BaseListBinding.bind(root)

        listBinding.listResultsRecyclerView.layoutManager = LinearLayoutManager(context)

        viewModel.data.observe(viewLifecycleOwner) {
            with(listBinding) {
                listLoadingProgress.visibility =
                    if (it.status == Status.LOADING)
                        View.VISIBLE else View.GONE

                listResultsRecyclerView.visibility =
                    if (it.status == Status.SUCCESS && it.data!!.isNotEmpty())
                        View.VISIBLE else View.GONE

                listNoResults.visibility =
                    if (it.status == Status.SUCCESS && it.data!!.isEmpty())
                        View.VISIBLE else View.GONE

                when (it.status) {
                    Status.SUCCESS -> {

                        if (it.data!!.isNotEmpty()) {
                            listResultsRecyclerView.adapter =
                                SearchResultsAdapter(it.data.toTypedArray()) { selectedMovie ->
                                    selectedContentViewModel.select(selectedMovie)
                                    //val action = MoviesFragmentDirections
                                    //    .actionMoviesToMovieDetails()
                                    findNavController().navigate(toContentDetailsAction)
                                }
                        }

                        if (it.message != null && !viewModel.errorShown.value!!) {
                            Snackbar.make(activity!!.window.decorView,
                                it.message.toString(), Snackbar.LENGTH_LONG).show()
                            viewModel.errorShown.value = true
                        }

                    }
                    Status.ERROR -> {
                        Snackbar.make(activity!!.window.decorView,  it.message.toString(), Snackbar.LENGTH_LONG).show()
                    }
                    else -> {
                        // not much anymore
                    }
                }
            }
        }

        return root
    }

    abstract fun setupView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View

    override fun onDestroyView() {
        super.onDestroyView()
        _listBinding = null
    }

    abstract fun createViewModel(): Lazy<VM>

    abstract fun createToContentActionDetails(): NavDirections

}