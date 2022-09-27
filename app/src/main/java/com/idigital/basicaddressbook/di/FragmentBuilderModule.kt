package com.idigital.basicaddressbook.di

import com.idigital.basicaddressbook.di.addcontact.AddContactViewModelModule
import com.idigital.basicaddressbook.di.contactdetails.ContactDetailsViewModelModule
import com.idigital.basicaddressbook.di.contactslist.ContactsListViewModelModule
import com.idigital.basicaddressbook.ui.addcontact.AddContactFragment
import com.idigital.basicaddressbook.ui.contactdetails.ContactDetailsFragment
import com.idigital.basicaddressbook.ui.contactslist.ContactsListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector(modules = [ContactsListViewModelModule::class])
    abstract fun contributeContactsFragment() : ContactsListFragment

    @ContributesAndroidInjector(modules = [AddContactViewModelModule::class])
    abstract fun contributeAddContactFragment() : AddContactFragment

    @ContributesAndroidInjector(modules = [ContactDetailsViewModelModule::class])
    abstract fun contributeContactDetailsFragment() : ContactDetailsFragment
}