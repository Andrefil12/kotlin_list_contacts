package com.project.contactslist.screens.contacts

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.project.contactslist.screens.database.Contacts

@BindingAdapter("myContacts")
fun TextView.nameContacts(item: Contacts?){
    item?.let {
        text = it.nameContact
    }
}