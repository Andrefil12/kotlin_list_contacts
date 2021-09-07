package com.project.contactslist.screens.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ContactsDatabaseDao {

    @Insert
    fun insert(contact: Contacts)

    @Update
    fun update(contact: Contacts)

    @Delete
    fun delete(key: Long)

    @Query("SELECT * FROM my_contacts ORDER BY name_contact")
    fun getAllContacts() : LiveData<List<Contacts>>

    @Query("SELECT * FROM my_contacts WHERE contactId = :key")
    fun searchContact(key: Long) : Contacts
}