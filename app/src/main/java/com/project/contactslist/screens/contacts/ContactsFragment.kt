package com.project.contactslist.screens.contacts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.project.contactslist.R
import com.project.contactslist.databinding.FragmentContactsBinding
import com.project.contactslist.screens.database.Contacts
import com.project.contactslist.screens.database.ContactsDatabase

class ContactsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentContactsBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_contacts, container, false
        )
        val application = requireNotNull(this.activity).application
        val dataSource = ContactsDatabase.getInstance(application).databaseDao
        val viewModelFactory = ContactsViewModelFactory(dataSource)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(ContactsViewModel::class.java)
        val adapter = ContactsAdapter(ContactsListener {
            idContact -> Toast.makeText(context, "$idContact", Toast.LENGTH_LONG).show()
            viewModel.onInsertKey(idContact)
        })
        binding.recycler.adapter = adapter
        viewModel.allContacts.observe(viewLifecycleOwner, Observer {
            it?.let{
                adapter.submitList(it)
            }
        })
        binding.addContact.setOnClickListener {
            it.findNavController().navigate(ContactsFragmentDirections.actionFragmentContactsToFragmentInsert())
        }
        viewModel.key.observe(viewLifecycleOwner, Observer { newKey ->
            newKey?.let {
                this.findNavController().navigate(ContactsFragmentDirections.actionFragmentContactsToFragmentShow(it))
                viewModel.onResetKey()
            }
        })
        return binding.root
    }

}