package com.example.contactsapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Contact::class], version = 1)
abstract class ContactDatabase : RoomDatabase() {

    // provides access to the DAO
    abstract fun contactDao(): ContactDao

    // used to implement Singleton pattern for DB instance
    companion object{

        // ensures that changes to INSTANCE are immdiately visible to
        // all threads
        @Volatile
        private var INSTANCE : ContactDatabase? = null

        fun getDatabase(context: Context): ContactDatabase {

            return INSTANCE ?: synchronized(this){

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ContactDatabase::class.java,
                    "contact_database"
                ).build()
                INSTANCE = instance
                instance

            }
        }


    }
}

