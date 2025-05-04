 

package com.code.todo.ui.item


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.ToDo.data.Item
import java.text.NumberFormat
import com.example.ToDo.data.ItemsRepository


/**
 * ViewModel to validate and insert items in the Room database.
 */

class ItemEntryViewModel(private val itemsRepository: ItemsRepository) : ViewModel() {

    /**
     * Holds current item ui state
     */
    var itemUiState by mutableStateOf(ItemUiState())
        private set

    /**
     * Updates the [itemUiState] with the value provided in the argument. This method also triggers
     * a validation for input values.
     */
    fun updateUiState(itemDetails: ItemDetails) {
        itemUiState =
            ItemUiState(itemDetails = itemDetails, isEntryValid = validateInput(itemDetails))
    }

    private fun validateInput(uiState: ItemDetails = itemUiState.itemDetails): Boolean {
        return with(uiState) {
            task.isNotBlank()
        }
    }

    suspend fun saveItem() {
        if (validateInput()) {
            itemsRepository.insertItem(itemUiState.itemDetails.toItem())
        }
    }
}

/**
 * Represents Ui State for an Item.
 */
data class ItemUiState(
    val itemDetails: ItemDetails = ItemDetails(),
    val isEntryValid: Boolean = false
)

data class ItemDetails(
    val id: Int = 0,
    val task: String = "",
)


fun ItemDetails.toItem(): Item = Item(
    id = id,
    task = task,

)



/**
 * Extension function to convert [Item] to [ItemUiState]
 */
fun Item.toItemUiState(isEntryValid: Boolean = false): ItemUiState = ItemUiState(
    itemDetails = this.toItemDetails(),
    isEntryValid = isEntryValid
)

/**
 * Extension function to convert [Item] to [ItemDetails]
 */
fun Item.toItemDetails(): ItemDetails = ItemDetails(
    id = id,
    task = task,

)



