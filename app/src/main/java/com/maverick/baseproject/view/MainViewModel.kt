package com.maverick.baseproject.view

import androidx.lifecycle.*
import com.maverick.baseproject.model.Country
import com.maverick.baseproject.repository.MainRepository
import com.maverick.baseproject.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val mainRepository: MainRepository,
) : ViewModel() {

    private val _dataState: MutableLiveData<DataState<List<Country>>> = MutableLiveData()

    val dataState: LiveData<DataState<List<Country>>>
        get() = _dataState

    fun setStateEvent(mainStateEvent: MainStateEvent) {
        viewModelScope.launch {
            when (mainStateEvent) {
                MainStateEvent.GetCountryList -> {
                    mainRepository.getCountryList()
                        .onEach { dataState ->
                            _dataState.value = dataState
                        }
                        .launchIn(viewModelScope)
                }
                MainStateEvent.None -> {
                    // who cares
                }
            }
        }
    }

}

sealed class MainStateEvent {
    object GetCountryList : MainStateEvent()
    object None : MainStateEvent()
}