package com.example.contactsapp.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Contact(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val phone: String
)
// TBALE: Contact
// id (INTEGER, PRIMARY KEY, AUTOINCREMENT)
// name (TEXT)
// phone (TEXT)


