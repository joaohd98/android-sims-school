package repositories.scores

class ScoresCourseResponse(
    var av1: Number = 0,
    var av2: Number = 0,
    var name: String = "",
    var skips: Number = 0
) {
    fun initService(result: Map<String, Any?>) {
        av1 = (result["av1"] as? Number ?: 0)
        av2 = (result["av2"] as? Number ?: 0)
        name = (result["name"] as? String ?: "")
        skips = (result["skips"] as? Number ?: 0)
    }

    fun averageScore(): String =  ((av1.toDouble() + av2.toDouble()) / 2.0).toString()

    fun skipsPercentage(): String = skips.toString().plus("%")

}
