package repositories.calendar

import repositories.scores.ScoresCourseResponse
import java.text.SimpleDateFormat
import java.util.*

@Suppress("UNCHECKED_CAST")
class CalendarResponse(
    var name: String = "",
    var weeks: ArrayList<CalendarWeekResponse> = arrayListOf()
) {
    fun initService(result: Map<String, Any?>) {
        name = (result["name"] as? String ?: "")

        val listWeeks = (result["weeks"] as ArrayList<*>)

        listWeeks.forEach {
            weeks.add(
                CalendarWeekResponse().apply {
                    initService(it as Map<String, Any>)
                }
            )
        }

    }

}
