package com.project.contactslist.screens.show

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.project.contactslist.R
import com.project.contactslist.databinding.FragmentShowBinding

class ShowFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentShowBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_show, container, false
        )
        return binding.root
    }

}