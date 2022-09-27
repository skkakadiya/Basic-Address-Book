package com.idigital.basicaddressbook.data

import android.content.Context
import android.content.res.AssetManager
import androidx.lifecycle.LiveData
import com.idigital.basicaddressbook.data.local.ContactDao
import org.w3c.dom.Node
import org.w3c.dom.Element
import javax.inject.Inject
import javax.inject.Singleton
import javax.xml.parsers.DocumentBuilderFactory

/*
* App written by Sanjay Kakadiya. You can call me any time for support if a problem occurs.
* Don't hesitate to contact me :)
* sk.kakadiya@gmail.com - +91 8866302277
*/

@Singleton
class ContactRepository @Inject constructor(
    private val context: Context,
    private val contactDao: ContactDao
) {

    fun getAllContacts(): LiveData<List<Contact>?> {

        val list = contactDao.getAllContacts()

        return list
    }

    suspend fun insertContact(contact: Contact) = contactDao.insertContact(contact)

    suspend fun deleteContact(contact: Contact?) = contactDao.deleteContact(contact)

    suspend fun updateContact(contact: Contact) = contactDao.updateContact(contact)

    fun findContactByName(query: String): LiveData<List<Contact>> =
        contactDao.findContactByName(query)

    fun getContactById(id: Int): LiveData<Contact?> = contactDao.getContactById(id)

    suspend fun addContactsFromFile() {

        val assetManager: AssetManager = context.assets
        try {
            val istream = assetManager.open("ab.xml")
            val builderFactory = DocumentBuilderFactory.newInstance()
            val docBuilder = builderFactory.newDocumentBuilder()
            val doc = docBuilder.parse(istream)
            val nList = doc.getElementsByTagName("Contact")
            for (i in 0 until nList.length) {
                if (nList.item(0).nodeType == Node.ELEMENT_NODE) {
                    val element = nList.item(i) as Element
                    contactDao.insertContact(
                        Contact(
                            getNodeValue("CustomerID", element),
                            getNodeValue("CompanyName", element),
                            getNodeValue("ContactName", element),
                            getNodeValue("ContactTitle", element),
                            getNodeValue("Address", element),
                            getNodeValue("City", element),
                            getNodeValue("Email", element),
                            getNodeValue("PostalCode", element),
                            getNodeValue("Country", element),
                            getNodeValue("Phone", element),
                            getNodeValue("Fax", element),
                        )
                    )
                }
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    // function to return node value
    private fun getNodeValue(tag: String, element: Element): String {
        val nodeList = element.getElementsByTagName(tag)
        val node = nodeList.item(0)
        if (node != null) {
            if (node.hasChildNodes()) {
                val child = node.firstChild
                while (child != null) {
                    if (child.nodeType === Node.TEXT_NODE) {
                        return child.nodeValue
                    }
                }
            }
        }
        return ""
    }

}