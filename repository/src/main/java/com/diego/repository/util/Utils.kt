package com.diego.repository.util

import java.lang.RuntimeException
import java.text.SimpleDateFormat
import java.util.Locale

object Utils {
    fun formatterDate(date: String): String =
        try {
            var simpleDateFormat = SimpleDateFormat("yyyy/mm/dd", Locale.getDefault())
            val newDate = simpleDateFormat.parse(date.replace("-", "/"))
            simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            simpleDateFormat.format(newDate)
        } catch (e: RuntimeException) {
            date
        }
}
