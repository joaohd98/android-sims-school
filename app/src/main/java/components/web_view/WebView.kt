package components.web_view

import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.joao.simsschool.R
import utils.CustomRoundBottomSheet


class WebViewFragment(private val url: Uri) : CustomRoundBottomSheet() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_web_view, container, false)
    }

    override fun onStart() {
        super.onStart()
        setFullScreen()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        fun invoke(fragmentManager: FragmentManager, uri: Uri) {
            val bottomSheetFragment = WebViewFragment(uri)

            bottomSheetFragment.show(fragmentManager, bottomSheetFragment.tag)
        }
    }
}