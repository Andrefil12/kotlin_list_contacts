package com.project.contactslist.screens.edit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.project.contactslist.R
import com.project.contactslist.databinding.FragmentEditBinding
import com.project.contactslist.screens.database.ContactsDatabase

class EditFragment : Fragment() {

    private lateinit var binding: FragmentEditBinding
    private var name: String = ""
    private var phone: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_edit, container, false
        )
        val application = requireNotNull(this.activity).application
        val dataSource = ContactsDatabase.getInstance(application).databaseDao
        val viewModelFactory = EditViewModelFactory(dataSource, application)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(EditViewModel::class.java)
        val args = EditFragmentArgs.fromBundle(requireArguments())
        viewModel.onCatchKey(args.editContact)
        viewModel.contact.observe(viewLifecycleOwner, Observer{ contact ->
            binding.editName.setText(contact.nameContact)
            binding.editPhone.setText(contact.phoneContact)
        })
        binding.editButton.setOnClickListener {
            catchField()
            viewModel.onUpdate(name, phone)
            it.findNavController().navigate(EditFragmentDirections.actionFragmentEditToFragmentShow(args.editContact))
        }
        return binding.root
    }

    private fun catchField(){
        name = binding.editName.text.toString()
        phone = binding.editPhone.text.toString()
    }

}