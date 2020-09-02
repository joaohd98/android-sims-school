package repositories.calendar

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import repositories.FirebaseInstances.firestore

class CalendarRepository {

    @Suppress("UNCHECKED_CAST")
    fun getCalendar(
        request: CalendarRequest,
        onSuccess: (List<CalendarResponse>) -> Unit,
        onError: (java.lang.Exception?) -> Unit
    ) {
        GlobalScope.launch(IO) {
            try {
                val classes = firestore
                    .collection("calendar")
                    .document(request.idClass)
                    .get()
                    .await()

                val months = classes.get("months") as ArrayList<*>

                val result: List<CalendarResponse> = months.map { result ->
                    CalendarResponse().apply {
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

