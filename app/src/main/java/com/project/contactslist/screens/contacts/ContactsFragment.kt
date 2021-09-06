package com.project.contactslist.screens.contacts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.project.contactslist.R
import com.project.contactslist.databinding.FragmentContactsBinding

class ContactsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentContactsBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_contacts, container, false
        )
        return binding.root
    }

}