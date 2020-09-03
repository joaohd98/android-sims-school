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
        onSuccess: (CalendarResponse) -> Unit,
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
                val result = CalendarResponse().apply {
                    initService(months)
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

