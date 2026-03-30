package com.example.contactsapp.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

// DAO: responsible for defining methods that access DB
@Dao
interface ContactDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContact(contact: Contact)

    @Query("SELECT * FROM contact")
    fun getAllContacts(): LiveData<List<Contact>>

    @Delete
    suspend fun deleteContact(contact: Contact)

}