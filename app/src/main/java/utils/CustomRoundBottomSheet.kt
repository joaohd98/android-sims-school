package utils

import android.app.Dialog
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_EXPANDED
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


open class CustomRoundBottomSheet: BottomSheetDialogFragment() {
    private lateinit var dialog : BottomSheetDialog

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        dialog = BottomSheetDialog(requireContext(), theme)

        dialog.setOnShowListener {
            dialog.behavior.addBottomSheetCallback(object :
                BottomSheetBehavior.BottomSheetCallback() {
                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                        dialog.dismiss()
                    }
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {
                    if (!slideOffset.isNaN()) dialog.window?.setDimAmount(0.5f - ((slideOffset * -1) / 2))
                }
            })
        }

        return dialog
    }

    fun set90Screen() {
        val height = (resources.displayMetrics.heightPixels * 0.9).toInt()
        setScreenSize(height)
    }

    fun setFullScreen() {
        val height = resources.displayMetrics.heightPixels
        setScreenSize(height)
    }

    private fun setScreenSize(height: Int) {
        dialog.behavior.state = STATE_EXPANDED
        dialog.behavior.peekHeight = height

        val layoutParams = FrameLayout.LayoutParams(view?.layoutParams!!)
        layoutParams.height = height
        view?.layoutParams = layoutParams
    }


}