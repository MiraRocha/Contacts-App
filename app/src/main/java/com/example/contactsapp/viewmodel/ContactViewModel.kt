package com.example.contactsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contactsapp.repo.ContactRepository
import com.example.contactsapp.room.Contact
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

// ViewModel is responsible for managing UI-related data for the
// Contact entiy & interacting with ContactRepository to perform
// data operations
@HiltViewModel
class ContactViewModel @Inject constructor(private val repository: ContactRepository) : ViewModel() {

    val allContacts: LiveData<List<Contact>> = repository.allContacts

    fun insert(contact: Contact) {
        viewModelScope.launch {
            repository.insert(contact)
        }
    }

    fun delete(contact: Contact) {
        viewModelScope.launch {
            repository.delete(contact)
        }
    }




}