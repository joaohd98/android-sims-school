package utils

import android.app.Activity
import android.app.AlertDialog
import com.joao.simsschool.R

fun Activity.alertDialog(message: String) {
    val builder = AlertDialog.Builder(this)
    builder.apply {
        setTitle("There is something wrong")
        setMessage(message)
        setPositiveButton(R.string.ok) { _, _ -> }
    }
    builder.create().show()
}