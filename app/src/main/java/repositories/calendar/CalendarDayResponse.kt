package repositories.calendar

import java.text.SimpleDateFormat
import java.util.*

class CalendarDayResponse(
    var course: String = "",
    var day: String = "",
    var homework: String = "",
    var test: String = "",
    var weekDay: Int = 0,
) {
    fun initService(result: Map<String, Any?>) {
        course = (result["course"] as? String ?: "")
        day = (result["day"] as? String ?: "")
        homework = (result["homework"] as? String ?: "")
        test = (result["test"] as? String ?: "")
        weekDay = (result["weekDay"] as? Number ?: 0).toInt()
    }
}