package com.idigital.basicaddressbook.di.addcontact

import androidx.lifecycle.ViewModel
import com.idigital.basicaddressbook.di.ViewModelKey
import com.idigital.basicaddressbook.ui.addcontact.AddContactViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AddContactViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AddContactViewModel::class)
    abstract fun bindAddContactViewModel(addContactViewModel: AddContactViewModel): ViewModel

}