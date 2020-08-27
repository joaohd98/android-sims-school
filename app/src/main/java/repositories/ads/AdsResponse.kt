package repositories.ads

import android.util.Log
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.tasks.await
import repositories.FirebaseInstances
import java.text.SimpleDateFormat
import java.util.*

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

            Log.d("aaa",  image)

            image = imageRes.await().toString()

        }
    }
}
