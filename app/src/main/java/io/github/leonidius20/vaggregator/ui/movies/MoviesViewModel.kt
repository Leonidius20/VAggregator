package io.github.leonidius20.vaggregator.ui.movies

import io.github.leonidius20.vaggregator.data.PieceOfContent
import io.github.leonidius20.vaggregator.data.Resource
import io.github.leonidius20.vaggregator.data.movies.MoviesRepository
import io.github.leonidius20.vaggregator.ui.base.search.BaseSearchViewModel

class MoviesViewModel : BaseSearchViewModel() {

    private val repository = MoviesRepository()

    override suspend fun obtainData(): Resource<List<PieceOfContent>> {
        return repository.findMoves(searchQuery)
    }

}