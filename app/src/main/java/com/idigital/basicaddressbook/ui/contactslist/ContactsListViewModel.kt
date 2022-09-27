package com.idigital.basicaddressbook.ui.contactslist

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.idigital.basicaddressbook.data.Contact
import com.idigital.basicaddressbook.data.ContactRepository
import kotlinx.coroutines.launch
import org.json.XML
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import javax.inject.Inject

/*
* App written by Sanjay Kakadiya. You can call me any time for support if a problem occurs.
* Don't hesitate to contact me :)
* sk.kakadiya@gmail.com - +91 8866302277
*/

class ContactsListViewModel  @Inject constructor(private val repository: ContactRepository): ViewModel() {

    val getAllContacts = repository.getAllContacts()

    fun findContactByName(query: String): LiveData<List<Contact>> {
        return repository.findContactByName(query)
    }

    fun addContactsFromFile() {
        viewModelScope.launch {
            repository.addContactsFromFile()
        }
    }

    fun exportJSON(context: Context): File {
        val f = File(context.cacheDir.toString() + "/ab.json")
        if (!f.exists()) try {
            val xmlStr = exportXML(context).readText()
            val jsonObj = XML.toJSONObject(xmlStr)
            f.writeText(jsonObj.toString())
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
        return f
    }

    fun exportXML(context: Context): File {
        val f = File(context.cacheDir.toString() + "/ab.xml")
        if (!f.exists()) try {
            val inputStream: InputStream = context.assets.open("ab.xml")
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            val fos = FileOutputStream(f)
            fos.write(buffer)
            fos.close()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
        return f
    }


}