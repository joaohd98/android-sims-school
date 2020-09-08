package repositories.tips

@Suppress("UNCHECKED_CAST")
class TipsMediasResponse(
    var url: String = "",
    var video: String = "",
    var image: String = ""
) {
    fun initService(result: Map<String, Any?>) {
        url = (result["url"] as? String ?: "")
        video = (result["video"] as? String ?: "")
        image = (result["image"] as? String ?: "")
    }

}

