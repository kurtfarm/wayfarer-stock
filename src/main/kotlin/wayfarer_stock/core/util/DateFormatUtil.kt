package wayfarer_stock.core.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter

object DateFormatUtil {

    private val dateFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")

    private fun getKoreanDayOfWeek(date: LocalDate): String {
        val dayOfWeek = date.dayOfWeek  // MONDAY, TUESDAY, ...
        return when (dayOfWeek) {
            java.time.DayOfWeek.MONDAY -> "월"
            java.time.DayOfWeek.TUESDAY -> "화"
            java.time.DayOfWeek.WEDNESDAY -> "수"
            java.time.DayOfWeek.THURSDAY -> "목"
            java.time.DayOfWeek.FRIDAY -> "금"
            java.time.DayOfWeek.SATURDAY -> "토"
            java.time.DayOfWeek.SUNDAY -> "일"
        }
    }

    fun formatWithDayOfWeek(date: LocalDate): String {
        val formattedDate = date.format(dateFormatter)
        val dayOfWeek = getKoreanDayOfWeek(date)
        return "$formattedDate($dayOfWeek)"
    }
}
