package com.project.contactslist.screens.show

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.project.contactslist.screens.database.ContactsDatabaseDao

class ShowViewModelFactory(
    private val dataSource: ContactsDatabaseDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ShowViewModel::class.java)){
            return ShowViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}