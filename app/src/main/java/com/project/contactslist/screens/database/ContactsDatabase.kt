package com.project.contactslist.screens.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Contacts::class], version = 1, exportSchema = false)
abstract class ContactsDatabase : RoomDatabase() {

    abstract val databaseDao: ContactsDatabaseDao

    companion object{
        private var INSTANCE: ContactsDatabase? = null

        fun getInstance(context: Context) : ContactsDatabase{
            var instance = INSTANCE
            if(instance == null){
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    ContactsDatabase::class.java,
                    "list_contacts"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
            }
            return instance
        }
    }
}