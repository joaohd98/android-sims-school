package repositories.calendar

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

class CalendarDayResponse(
    var course: String = "",
    var day: String = "",
    var homework: String = "",
    var teacher: String = "",
    var test: String = "",
    var weekDay: Int = 0,
) {
    fun initService(result: Map<String, Any?>) {
        course = (result["course"] as? String ?: "")
        day = (result["day"] as? String ?: "")
        homework = (result["homework"] as? String ?: "")
        teacher = (result["teacher"] as? String ?: "")
        test = (result["test"] as? String ?: "")
        weekDay = (result["weekday"] as? Number ?: 0).toInt()
    }

    fun getDayFormatted() = day.split("/")[0]
}
