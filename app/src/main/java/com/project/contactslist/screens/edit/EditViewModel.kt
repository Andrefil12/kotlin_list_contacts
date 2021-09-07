package com.project.contactslist.screens.edit

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.project.contactslist.screens.database.Contacts
import com.project.contactslist.screens.database.ContactsDatabaseDao
import kotlinx.coroutines.launch

class EditViewModel(
    private val database: ContactsDatabaseDao,
    application: Application
) : AndroidViewModel(application) {

    private val _contact = MutableLiveData<Contacts>()
    val contact: LiveData<Contacts>
        get() = _contact
    private val keyContact = MutableLiveData<Long>()

    fun onCatchKey(key: Long) {
        keyContact.value = key
        onEditContact()
    }

    private fun onEditContact(){
        viewModelScope.launch {
            val contactId = keyContact.value
            getContact(contactId)
        }
    }

    private suspend fun getContact(id: Long?){
        _contact.value = id?.let{
            database.getContact(id)
        }
    }
}