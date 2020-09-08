package repositories.tips

import kotlin.collections.ArrayList

@Suppress("UNCHECKED_CAST")
class TipsResponse(
    var name: String = "",
    var medias: ArrayList<TipsMediasResponse> = arrayListOf()
) {
    fun initService(result: Map<String, Any?>) {
        name = (result["name"] as? String ?: "")

        val listMedias = (result["medias"] as ArrayList<*>)

        listMedias.forEach {
            medias.add(
                TipsMediasResponse().apply {
                    initService(it as Map<String, Any>)
                }
            )
        }
    }

}
