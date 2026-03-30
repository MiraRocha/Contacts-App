package com.example.contactsapp.repo

import androidx.lifecycle.LiveData
import com.example.contactsapp.room.Contact
import com.example.contactsapp.room.ContactDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ContactRepository @Inject constructor (private val contactDao: ContactDao){

    // Updates the UI when the data changes
    val allContacts: LiveData<List<Contact>> = contactDao.getAllContacts()

    suspend fun insert(contact: Contact){
        contactDao.insertContact(contact)
    }

    suspend fun delete(contact: Contact){
        contactDao.deleteContact(contact)
    }
}
