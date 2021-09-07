package com.project.contactslist.screens.contacts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.contactslist.screens.database.ContactsDatabaseDao
import kotlinx.coroutines.launch


class ContactsViewModel(
    private val database: ContactsDatabaseDao
) : ViewModel(){

    private val searchContact = MutableLiveData<String>()
    private val allContacts = database.getAllContacts()

    fun onSearch(nameContact: String){
        searchContact.value = nameContact
        queryContact()
    }

    private fun queryContact(){
        viewModelScope.launch {
            val name = searchContact.value.toString()
            onSearchContact(name)
        }
    }

    private suspend fun onSearchContact(name: String){
        val nameContact = database.searchContact(name)
    }
}