package com.project.contactslist.screens.contacts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.project.contactslist.screens.database.ContactsDatabaseDao

class ContactsViewModelFactory(
    private val dataSource: ContactsDatabaseDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ContactsViewModel::class.java)){
            return ContactsViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}