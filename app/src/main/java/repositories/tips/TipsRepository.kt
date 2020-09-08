package repositories.tips

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import repositories.FirebaseInstances.firestore

class TipsRepository {

    @Suppress("UNCHECKED_CAST")
    fun getTips(
        request: TipsRequest,
        onSuccess: (List<TipsResponse>) -> Unit,
        onError: (java.lang.Exception?) -> Unit
    ) {
        GlobalScope.launch(IO) {
            try {
                val classes = firestore
                    .collection("tips")
                    .document(request.idClass)
                    .get()
                    .await()

                val tips = classes.get("tips") as ArrayList<*>

                val result: List<TipsResponse> = tips.map { it ->
                    TipsResponse().apply {
                        initService(it as Map<String, Any>)
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

