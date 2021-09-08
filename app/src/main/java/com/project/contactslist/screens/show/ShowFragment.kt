package com.project.contactslist.screens.show

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
import com.project.contactslist.databinding.FragmentShowBinding
import com.project.contactslist.screens.database.ContactsDatabase

class ShowFragment : Fragment() {

    private lateinit var binding: FragmentShowBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_show, container, false
        )
        val application = requireNotNull(this.activity).application
        val dataSource = ContactsDatabase.getInstance(application).databaseDao
        val viewModelFactory = ShowViewModelFactory(dataSource)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(ShowViewModel::class.java)
        val args = ShowFragmentArgs.fromBundle(requireArguments())
        viewModel.recoverArgs(args.keyContact)
        viewModel.name.observe(viewLifecycleOwner, Observer { newName ->
            newName?.let {
                binding.nameContactShow.text = it
            }
        })
        viewModel.phone.observe(viewLifecycleOwner, Observer { newPhone ->
            newPhone?.let {
                binding.numberPhoneShow.text = it
            }
        })
        binding.buttonShow.setOnClickListener {
            it.findNavController().navigate(ShowFragmentDirections.actionFragmentShowToFragmentEdit(args.keyContact))
        }
        return binding.root
    }

}