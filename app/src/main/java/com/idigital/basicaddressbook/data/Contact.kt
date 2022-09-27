package com.idigital.basicaddressbook.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/*
* App written by Sanjay Kakadiya. You can call me any time for support if a problem occurs.
* Don't hesitate to contact me :)
* sk.kakadiya@gmail.com - +91 8866302277
*/

@Entity
data class Contact(
    var CustomerID: String?,
    var CompanyName: String?,
    var ContactName: String?,
    var ContactTitle: String?,
    var Address: String?,
    var City: String?,
    var Email: String?,
    var PostalCode: String?,
    var Country: String?,
    var Phone: String?,
    var Fax: String?,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)