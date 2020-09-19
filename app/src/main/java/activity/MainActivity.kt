package activity

import android.content.res.Resources
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.joao.simsschool.R
import utils.CacheVideoTemp

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun getTheme(): Resources.Theme {
        return ContextThemeWrapper(baseContext, R.style.AppTheme).theme
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            val imm: InputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
            window.decorView.clearFocus();
        }

        return super.dispatchTouchEvent(ev)
    }

    override fun onDestroy() {
        super.onDestroy()
        CacheVideoTemp.deleteAllCache(this)
    }
}