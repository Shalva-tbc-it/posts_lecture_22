package com.example.posts.presentation.extension

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Long.toFormattedDate(): String {
    val date = Date(this * 1000)
    val dateFormat = SimpleDateFormat("dd MMMM 'at' h:mm a", Locale.getDefault())
    return dateFormat.format(date)
}