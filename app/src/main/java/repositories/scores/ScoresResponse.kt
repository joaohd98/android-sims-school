package repositories.scores

@Suppress("UNCHECKED_CAST")
class ScoresResponse(
    var semester: Int = 0,
    var courses: ArrayList<ScoresCourseResponse> = arrayListOf()
) {
    fun initService(result: Map<String, Any?>) {
        semester = (result["number"] as? Int ?: 0)

        val listCourses = (result["courses"] as ArrayList<*>)

        listCourses.forEach {
            courses.add(
                ScoresCourseResponse().apply {
                    initService(it as Map<String, Any>)
                }
            )
        }
    }

}

