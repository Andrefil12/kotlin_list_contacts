package com.project.contactslist.screens.contacts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.contactslist.screens.database.ContactsDatabaseDao
import kotlinx.coroutines.launch


class ContactsViewModel(
    private val database: ContactsDatabaseDao
) : ViewModel(){

    private val searchContact = MutableLiveData<String>()
    private val _key = MutableLiveData<Long>()
    val key: LiveData<Long>
        get() = _key
    val allContacts = database.getAllContacts()

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

    fun onInsertKey(id: Long){
        _key.value = id
    }

    fun onResetKey(){
        _key.value = null
    }
}