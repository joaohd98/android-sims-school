package repositories.ads

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.tasks.await
import repositories.FirebaseInstances

class AdsResponse(
    var image: String = "",
    var url: String = ""
) {
    suspend fun initService(result: Map<String, Any?>) {
        coroutineScope {
            url = (result["url"] as? String ?: "")

            val imageUrl = result["image"]  as String
            val imageRes = async {
                FirebaseInstances.storage.reference.child(imageUrl).downloadUrl.await()
            }

            image = imageRes.await().toString()

        }
    }
}
