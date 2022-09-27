package com.idigital.basicaddressbook.ui.contactdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.idigital.basicaddressbook.data.Contact
import com.idigital.basicaddressbook.data.ContactRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class ContactDetailsViewModel @Inject constructor(private val repository: ContactRepository): ViewModel() {

    fun getContactByInt(contactId: Int) = repository.getContactById(contactId)

    private fun deleteRepositoryContact(contact: Contact?){
        viewModelScope.launch {
            repository.deleteContact(contact)
        }
    }

    fun deleteContact(contact: Contact?){
        deleteRepositoryContact(contact)
    }
}