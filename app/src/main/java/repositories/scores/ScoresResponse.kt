package repositories.scores

import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

@Suppress("UNCHECKED_CAST")
class ScoresResponse(
    var semester: Int = 0,
    var courses: List<ScoresCourseResponse> = listOf(),
    var isSelected: Boolean = false
) {
    fun initService(result: Map<String, Any?>) {
        semester = (result["semester"] as? Int ?: 0)

        val results = (result["courses"] as ArrayList<*>)

        val listCourses: List<ScoresCourseResponse> = results.map {
            ScoresCourseResponse().apply {
                initService(it as Map<String, Any>)
            }
        }

        courses = listCourses
    }

}

