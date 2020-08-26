package repositories.classes

import java.text.SimpleDateFormat
import java.util.*

class ClassesResponse(
    var formattedData: String = "",
    var course: String = "",
    var weekDay: String = "",
    var hasClass: Boolean = false,
    var place: String = "",
    var teacher: String = "",
) {
    fun initService(result: Map<String, Any?>) {
        course = (result["course"] as? String ?: "")
        weekDay = (result["weekDay"] as? String ?: "")
        hasClass = (result["hasClass"] as? Boolean ?: false)
        place =  (result["place"] as? String ?: "")
        teacher = (result["teacher"] as? String ?: "")

        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_WEEK, getDayWeekNumber());

        val format = SimpleDateFormat("dd/MM/yyyy", Locale.US)
        val timestamp = format.format(calendar.time)

        formattedData = "$timestamp - $weekDay"
    }

    private fun getDayWeekNumber(): Int {
        return when(weekDay) {
            "Sunday" -> Calendar.SUNDAY
            "Monday" -> Calendar.MONDAY
            "Tuesday" -> Calendar.TUESDAY
            "Wednesday" -> Calendar.WEDNESDAY
            "Thursday" -> Calendar.THURSDAY
            "Saturday" -> Calendar.SATURDAY
            else -> 0
        }
    }

}
