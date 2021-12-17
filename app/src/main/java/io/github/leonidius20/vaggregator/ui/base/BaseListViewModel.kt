package io.github.leonidius20.vaggregator.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.leonidius20.vaggregator.data.PieceOfContent
import io.github.leonidius20.vaggregator.data.Resource
import kotlinx.coroutines.launch

abstract class BaseListViewModel: ViewModel() {

    val data = MutableLiveData<Resource<List<PieceOfContent>>>()

    val errorShown = MutableLiveData(false) // temp code to not re-show error messages

    fun loadData() {
        data.value = Resource.loading(null)
        errorShown.value = false
        viewModelScope.launch {
            try {
                data.value = obtainData()
            } catch (e: Exception) {
                data.value = Resource.error(e.message!!, null)
            }
        }
    }

    abstract suspend fun obtainData(): Resource<List<PieceOfContent>>

}
