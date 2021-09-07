package com.project.contactslist.screens.insert

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.project.contactslist.screens.database.ContactsDatabaseDao
import java.lang.IllegalArgumentException

class InsertViewModelFactory(
    private val dataSource: ContactsDatabaseDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(InsertViewModel::class.java)){
            return InsertViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}