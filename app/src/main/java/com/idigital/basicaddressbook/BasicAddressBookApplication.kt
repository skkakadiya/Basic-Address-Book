package com.idigital.basicaddressbook

import com.idigital.basicaddressbook.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

/*
* App written by Sanjay Kakadiya. You can call me any time for support if a problem occurs.
* Don't hesitate to contact me :)
* sk.kakadiya@gmail.com - +91 8866302277
*/

class BasicAddressBookApplication : DaggerApplication(){

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(applicationContext)
    }
}