package com.idigital.basicaddressbook.di.contactslist

import androidx.lifecycle.ViewModel
import com.idigital.basicaddressbook.di.ViewModelKey
import com.idigital.basicaddressbook.ui.contactslist.ContactsListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ContactsListViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ContactsListViewModel::class)
    abstract fun bindContactsListViewModel(contactsListViewModel: ContactsListViewModel): ViewModel

}