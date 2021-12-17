package io.github.leonidius20.vaggregator.ui.library

import io.github.leonidius20.vaggregator.data.PieceOfContent
import io.github.leonidius20.vaggregator.data.Resource
import io.github.leonidius20.vaggregator.data.library.LibraryRepository
import io.github.leonidius20.vaggregator.ui.base.list.BaseListViewModel

class LibraryViewModel : BaseListViewModel() {

    private val repository = LibraryRepository.instance

    override suspend fun obtainData(): Resource<List<PieceOfContent>> {
        return Resource.success(repository.getAll())
    }

}