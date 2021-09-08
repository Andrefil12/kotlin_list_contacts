package com.project.contactslist.screens.insert

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.project.contactslist.R
import com.project.contactslist.databinding.FragmentInsertBinding
import com.project.contactslist.screens.database.ContactsDatabase

class InsertFragment : Fragment() {

    private lateinit var binding: FragmentInsertBinding
    private var name: String = ""
    private var phone: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_insert, container, false
        )
        val application = requireNotNull(this.activity).application
        val dataSource = ContactsDatabase.getInstance(application).databaseDao
        val viewModelFactory = InsertViewModelFactory(dataSource)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(InsertViewModel::class.java)
        binding.editButton.setOnClickListener {
            insertField()
            viewModel.onInsertContact(name, phone)
            it.findNavController().navigate(InsertFragmentDirections.actionFragmentInsertToFragmentContacts())
        }
        return binding.root
    }

    private fun insertField(){
        name = binding.editName.text.toString()
        phone = binding.editPhone.text.toString()
    }

}