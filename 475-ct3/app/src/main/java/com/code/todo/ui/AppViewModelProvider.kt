 

package com.example.ToDo.ui

import android.app.Application
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ToDo.ToDoApplication
import com.example.ToDo.ui.home.HomeViewModel
import com.example.ToDo.ui.item.ItemDetailsViewModel
import com.example.ToDo.ui.item.ItemEditViewModel
import com.example.ToDo.ui.item.ItemEntryViewModel

/**
 * Provides Factory to create instance of ViewModel for the entire ToDo app
 */
object AppViewModelProvider {
    val Factory = viewModelFactory {
        // Initializer for ItemEditViewModel
        initializer {
            ItemEditViewModel(
                this.createSavedStateHandle()
            )
        }
        initializer {
            HomeViewModel(ToDoApplication().container.itemsRepository)
        }

        // Initializer for ItemEntryViewModel
        initializer {
            ItemEntryViewModel(ToDoApplication().container.itemsRepository)
        }

        // Initializer for ItemDetailsViewModel
        initializer {
            ItemDetailsViewModel(
                this.createSavedStateHandle(),
                ToDoApplication().container.itemsRepository
            )
        }


    }
}

/**
 * Extension function to queries for [Application] object and returns an instance of
 * [ToDoApplication].
 */
fun CreationExtras.ToDoApplication(): ToDoApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as ToDoApplication)
