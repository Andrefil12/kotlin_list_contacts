package com.project.contactslist.screens.show

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.contactslist.screens.database.Contacts
import com.project.contactslist.screens.database.ContactsDatabaseDao
import kotlinx.coroutines.launch

class ShowViewModel(
    private val database: ContactsDatabaseDao
) : ViewModel() {
    private val _name = MutableLiveData<String>()
    val name: LiveData<String>
        get() = _name
    private val _phone = MutableLiveData<String>()
    val phone: LiveData<String>
        get() = _phone
    private val id = MutableLiveData<Long>()

    fun recoverArgs(key: Long){
        id.value = key
        get()
    }

    private fun get(){
        viewModelScope.launch {
            val myId = id.value
            getContact(myId)
        }
    }

    private suspend fun getContact(key: Long?) {
        val contact = key?.let {
            database.getContact(it)
        }
        onInsertField(contact)
    }

    private fun onInsertField(contact: Contacts?){
        contact?.let {
            _name.value = it.nameContact
            _phone.value = it.phoneContact
        }
    }
}