package repositories.tips

import android.app.Application
import kotlinx.coroutines.coroutineScope
import repositories.FirebaseInstances


@Suppress("UNCHECKED_CAST")
class TipsResponse(
    var name: String = "",
    var medias: ArrayList<TipsMediasResponse> = arrayListOf(),
    var thumbnailImage: String = "",
    var thumbnailVideo: String = "",
    var currentMediaPosition: Int = 0
) {
    suspend fun initService(result: Map<String, Any?>, application: Application) {
        coroutineScope {
            name = (result["name"] as? String ?: "")

            val listMedias = (result["medias"] as ArrayList<*>)

            listMedias.forEach {
                medias.add(
                    TipsMediasResponse().apply {
                        initService(it as Map<String, Any>)
                    }
                )
            }

            val media = medias.random()

            FirebaseInstances.getURLFromMedia(media.image)?.let {
                thumbnailImage = it
            }

            FirebaseInstances.getURLFromMedia(media.video)?.let {
                thumbnailVideo = it
            }
            
        }
    }


    fun getMedia(): TipsMediasResponse {
        return medias[currentMediaPosition]
    }

    fun getMediaSize(): Int {
        return medias.size
    }

    fun goLeftMedia(onFirst: () -> Unit) {
        if(currentMediaPosition <= 0) {
            onFirst()
        }
        else {
            currentMediaPosition--
        }
    }


    fun goRightMedia(onLast: () -> Unit) {
        if(currentMediaPosition >= medias.size - 1) {
            onLast()
        }
        else {
            currentMediaPosition++
        }
    }

    fun reset() {
        currentMediaPosition = 0

        medias.forEach {
            it.reset()
        }
    }
}
