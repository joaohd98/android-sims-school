package repositories.calendar

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
