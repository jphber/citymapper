package com.jeanbernuy.citymapper.core

import java.text.SimpleDateFormat

//Function on a date class which returns a string
fun dateToString(format: String): String {
    val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    val formatter = SimpleDateFormat("HH:mm")
    return formatter.format(parser.parse(format))
}