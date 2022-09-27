package com.idigital.basicaddressbook.ui.addcontact

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.idigital.basicaddressbook.data.Contact
import com.idigital.basicaddressbook.data.ContactRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddContactViewModel @Inject constructor(private val repository: ContactRepository): ViewModel()  {

    var CustomerID = ""
    var CompanyName = ""
    var ContactName = ""
    var ContactTitle = ""
    var Address = ""
    var City = ""
    var Email = ""
    var PostalCode = ""
    var Country = ""
    var Phone = ""
    var Fax = ""

    private fun updateRepositoryContact(contact: Contact){
        viewModelScope.launch {
            repository.updateContact(contact)
        }
    }

    fun updateContact(contact: Contact){
        updateRepositoryContact(contact)
    }

    fun saveContact(contact: Contact){
        insertContact(contact)
    }

    private fun insertContact(contact: Contact){
        viewModelScope.launch {
            repository.insertContact(contact)
        }
    }

    fun getContactById(contactId: Int) = repository.getContactById(contactId)
}