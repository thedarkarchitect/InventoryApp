package com.example.inventory.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inventory.data.Item
import com.example.inventory.data.ItemsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

/**
 * ViewModel to retrieve all items in the Room database.
 */
class HomeViewModel(itemsRepository: ItemsRepository) : ViewModel() {
    val homeUiState: StateFlow<HomeUiState> =
        itemsRepository.getAllItemsStream().map { HomeUiState(it) }//this is a FLow that is converted to a stateFlow using the StateIn
        .stateIn(
            scope = viewModelScope,//this defines the lifecycle of the StateFlow, note that when the viewModelScope is canceled, the StateFlow is also canceled
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),//The pipeline should only be active when the UI is visible, to configure a delay between the disappearance of the last subscriber and the stopping of the sharing coroutine
            initialValue = HomeUiState()//this is the initial value of the state flow
        )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L//used as a delay during recomposition
    }
}

/**
 * Ui State for HomeScreen
 */
data class HomeUiState(val itemList: List<Item> = listOf())
