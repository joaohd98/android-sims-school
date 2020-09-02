package repositories.calendar

import java.text.SimpleDateFormat
import java.util.*

@Suppress("UNCHECKED_CAST")
class CalendarWeekResponse(
    var number: Int = 0,
    var days: ArrayList<CalendarDayResponse> = arrayListOf()
) {
    fun initService(result: Map<String, Any?>) {
        number = (result["number"] as? Number ?: 0).toInt()

        val listDay = (result["days"] as ArrayList<*>)

        listDay.forEach {
            days.add(
                CalendarDayResponse().apply {
                    initService(it as Map<String, Any>)
                }
            )
        }

    }
}
