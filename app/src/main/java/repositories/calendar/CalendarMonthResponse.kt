package repositories.calendar

import screens.logged.classes.components.ClassesCalendarAdapter
import java.util.*
import kotlin.collections.ArrayList

@Suppress("UNCHECKED_CAST")
class CalendarMonthResponse(
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
