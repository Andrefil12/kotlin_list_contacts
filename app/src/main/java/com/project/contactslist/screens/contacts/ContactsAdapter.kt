package com.project.contactslist.screens.contacts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.project.contactslist.databinding.LayoutContactsBinding
import com.project.contactslist.screens.database.Contacts

class ContactsAdapter(val listener: ContactsListener) : ListAdapter<Contacts, ContactsAdapter.ViewHolder>(ContactsDiffCallback()) {

    class ContactsDiffCallback : DiffUtil.ItemCallback<Contacts>(){
        override fun areItemsTheSame(oldItem: Contacts, newItem: Contacts): Boolean {
            return oldItem.contactId == newItem.contactId
        }

        override fun areContentsTheSame(oldItem: Contacts, newItem: Contacts): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, listener)
    }

    class ViewHolder private constructor(val binding: LayoutContactsBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item: Contacts, click: ContactsListener){
            binding.itemContact = item
            binding.executePendingBindings()
            binding.clickListener = click

        }

        companion object{
            fun from(parent: ViewGroup) : ViewHolder{
                val inflater = LayoutInflater.from(parent.context)
                val binding = LayoutContactsBinding.inflate(inflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class ContactsListener(val listener: (id: Long) -> Unit){
    fun onClick(item: Contacts) = listener(item.contactId)
}