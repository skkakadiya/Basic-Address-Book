package com.idigital.basicaddressbook.util

import androidx.navigation.navOptions
import com.idigital.basicaddressbook.R

const val DATABASE_NAME = "contacts_list_db"

val OPTIONS = navOptions {
    anim {
        enter = R.anim.fade_in
        exit = R.anim.fade_out
        popEnter = R.anim.slide_in_left
        popExit = R.anim.slide_out_right
    }
}