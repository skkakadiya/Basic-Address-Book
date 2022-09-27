package com.idigital.basicaddressbook.di.contactdetails

import androidx.lifecycle.ViewModel
import com.idigital.basicaddressbook.di.ViewModelKey
import com.idigital.basicaddressbook.ui.contactdetails.ContactDetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ContactDetailsViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ContactDetailsViewModel::class)
    abstract fun bindContactDetailsViewModel(contactDetailsViewModel: ContactDetailsViewModel): ViewModel
}