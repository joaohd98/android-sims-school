package repositories.scores

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import repositories.FirebaseInstances.firestore

class ScoresRepository {

    @Suppress("UNCHECKED_CAST")
    fun getScores(
        request: ScoresRequest,
        onSuccess: (List<ScoresResponse>) -> Unit,
        onError: (java.lang.Exception?) -> Unit
    ) {
        GlobalScope.launch(IO) {
            try {
                val classes = firestore
                    .collection("score")
                    .document(request.idUser)
                    .get()
                    .await()

                val days = classes.get("semesters") as ArrayList<*>

                val result: List<ScoresResponse> = days.map { result ->
                    ScoresResponse().apply {
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

