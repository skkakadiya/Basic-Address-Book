package com.idigital.basicaddressbook.di

import android.content.Context
import androidx.room.Room
import com.idigital.basicaddressbook.data.ContactRepository
import com.idigital.basicaddressbook.data.local.ContactDao
import com.idigital.basicaddressbook.data.local.ContactRoomDatabase
import com.idigital.basicaddressbook.util.DATABASE_NAME
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object AppModule {

    @JvmStatic
    @Singleton
    @Provides
    fun provideContactDao(contactRoomDatabase: ContactRoomDatabase): ContactDao {
        return contactRoomDatabase.contactDao()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideContactDatabase(context: Context): ContactRoomDatabase {
        return Room.databaseBuilder(context, ContactRoomDatabase::class.java, DATABASE_NAME).build()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideContactRepository(context: Context, contactDao: ContactDao): ContactRepository {
        return ContactRepository(context, contactDao)
    }

}