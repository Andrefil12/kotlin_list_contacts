package com.project.contactslist.screens.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "my_contacts")
data class Contacts(
    @PrimaryKey(autoGenerate = true)
    var contactId: Long = 0L,

    @ColumnInfo(name = "name_contact")
    var nameContact: String = "",

    @ColumnInfo(name = "phone_contact")
    var phoneContact: String = ""
){

}