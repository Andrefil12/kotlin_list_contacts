package com.project.contactslist.screens.insert

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.contactslist.screens.database.Contacts
import com.project.contactslist.screens.database.ContactsDatabaseDao
import kotlinx.coroutines.launch

class InsertViewModel(
    private val database: ContactsDatabaseDao
) : ViewModel() {

    fun onInsertContact(name: String, phone: String) {
        viewModelScope.launch {
            val newContact = Contacts()
            newContact.nameContact = name
            newContact.nameContact = phone
            insert(newContact)
        }
    }

    private suspend fun insert(contact: Contacts){
        database.insert(contact)
    }
}