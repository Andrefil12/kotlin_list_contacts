package com.project.contactslist.screens.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ContactsDatabaseDao {

    @Insert
    suspend fun insert(contact: Contacts)

    @Update
    suspend fun update(contact: Contacts)

//    @Delete
//    suspend fun delete(key: Long)

    @Query("SELECT * FROM my_contacts ORDER BY name_contact")
    fun getAllContacts() : LiveData<List<Contacts>>

    @Query("SELECT * FROM my_contacts WHERE contactId = :key")
    suspend fun getContact(key: Long) : Contacts

    @Query("SELECT * FROM my_contacts WHERE name_contact = :name")
    suspend fun searchContact(name: String) : Contacts?
}