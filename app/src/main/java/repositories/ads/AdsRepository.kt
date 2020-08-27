package repositories.ads

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import repositories.FirebaseInstances.firestore

class AdsRepository {

    @Suppress("UNCHECKED_CAST")
    fun getAds(
        onSuccess: (List<AdsResponse>) -> Unit,
        onError: (java.lang.Exception?) -> Unit
    ) {
        GlobalScope.launch(IO) {
            try {
                val classes = firestore
                    .collection("ads")
                    .document("all")
                    .get()
                    .await()

                val ads = classes.get("ads") as ArrayList<*>

                val result: List<AdsResponse> = ads.map { result ->
                    AdsResponse().apply {
                        initService(result as Map<String, Any>)
                    }
                }

                GlobalScope.launch(Dispatchers.Main) {
                    onSuccess(result)
                }
            }
            catch (exception: Exception) {
                GlobalScope.launch(Dispatchers.Main) {
                    onError(exception)
                }
            }
        }
    }
}

