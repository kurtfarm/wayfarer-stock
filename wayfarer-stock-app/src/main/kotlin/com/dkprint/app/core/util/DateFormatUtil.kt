package com.dkprint.app.core.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

object DateFormatUtil {

    private val dateFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
    private val getKoreanDateFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd (E)", Locale.KOREAN)

    fun formatWithDayOfWeek(date: LocalDate): String {
        return date.format(getKoreanDateFormatter)
    }

    fun formatDate(date: LocalDate): String{
        return date.format(dateFormatter)
    }
}
