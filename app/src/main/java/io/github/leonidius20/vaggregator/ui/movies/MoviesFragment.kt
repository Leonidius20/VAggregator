package io.github.leonidius20.vaggregator.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.mancj.materialsearchbar.MaterialSearchBar
import io.github.leonidius20.vaggregator.databinding.FragmentMoviesBinding

class MoviesFragment : Fragment(), MaterialSearchBar.OnSearchActionListener {

    private val moviesViewModel: MoviesViewModel by viewModels()
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

        val textView: TextView = binding.textDashboard
        moviesViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        moviesViewModel.movies.observe(viewLifecycleOwner) {
            // TODO: display the movies
            Toast.makeText(context, "Loaded ${it.size} movies", Toast.LENGTH_SHORT).show()
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
        moviesViewModel.loadMovies(text.toString())
    }

    override fun onButtonClicked(buttonCode: Int) {

    }

}