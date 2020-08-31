package repositories.scores

class ScoresCourseResponse(
    var av1: Int = 0,
    var av2: Int = 0,
    var name: String = "",
    var skips: Int = 0
) {
    fun initService(result: Map<String, Any?>) {
        av1 = (result["av1"] as? Int ?: 0)
        av2 = (result["av1"] as? Int ?: 0)
        name = (result["name"] as? String ?: "")
        skips = (result["av1"] as? Int ?: 0)
    }
}
