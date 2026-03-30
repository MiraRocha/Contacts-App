package com.example.contactsapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactsapp.databinding.ActivityMainBinding
import com.example.contactsapp.room.Contact
import com.example.contactsapp.util.ContactAdapter
import com.example.contactsapp.viewmodel.ContactViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ContactAdapter

    // viewModels() delegate ensures that the ViewModel is
    // scoped to the current Activity, meaning it will survive
    // configuration changes
    private val viewModel: ContactViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ContactAdapter { contact ->
            viewModel.delete(contact)
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        viewModel.allContacts.observe(this) { contacts ->
            adapter.submitList(contacts)
        }

        // Inserting new contact into ROOM DB
        binding.addButton.setOnClickListener {
            val name = binding.nameEditText.text.toString()
            val phone = binding.phoneEditText.text.toString()

            if(name.isNotEmpty() && phone.isNotEmpty()){
                val contact = Contact(name = name, phone = phone)
                viewModel.insert(contact)
            }

        }







    }
}