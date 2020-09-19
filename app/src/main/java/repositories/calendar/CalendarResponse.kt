package repositories.calendar

import screens.logged.classes.components.ClassesCalendarAdapter
import java.util.*

@Suppress("UNCHECKED_CAST")
class CalendarResponse(
    var months: ArrayList<CalendarMonthResponse> = arrayListOf()
) {

    class RecyclerViewModel (
        val typeView: Int,
        val month: String?,
        val response: CalendarWeekResponse?
    )

    var recyclerViews: ArrayList<RecyclerViewModel> = arrayListOf()
    var recyclerViewsIndex = 0

    fun initService(listMonths: ArrayList<*>) {

        listMonths.forEach { result ->
            months.add(
                CalendarMonthResponse().apply {
                    initService(result as Map<String, Any>)
                }
            )
        }

        initRecyclerView()
    }

    private fun initRecyclerView() {
        val currentMonth = Calendar.getInstance().get(Calendar.MONTH)

        months.forEachIndexed { index, month ->
            if (index == currentMonth) {
                recyclerViewsIndex = recyclerViews.size
            }

            recyclerViews.add(
                RecyclerViewModel(
                    ClassesCalendarAdapter.TYPE_MONTH,
                    month.name,
                    null
                )
            )

            month.weeks.forEach { week ->
                recyclerViews.add(
                    RecyclerViewModel(
                        ClassesCalendarAdapter.TYPE_WEEK,
                        null,
                        week
                    )
                )
            }
        }
    }

}
