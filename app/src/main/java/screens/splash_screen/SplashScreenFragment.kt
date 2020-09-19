package screens.splash_screen

import android.os.Bundle
import android.os.Handler
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.joao.simsschool.R
import com.joao.simsschool.databinding.ComponentsProgressBarCircularBinding.inflate
import repositories.FirebaseInstances


class SplashScreenFragment : Fragment() {
    private val viewModel: SplashScreenViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.splash_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.user.observe(viewLifecycleOwner, {
            val firebaseUser = FirebaseInstances.auth.currentUser

            val action = if(it != null && firebaseUser != null) {
                R.id.action_splashScreenFragment_to_tabsFragment
            }
            else {
                R.id.action_splashScreenFragment_to_loginScreen
            }

            Handler().postDelayed({
                findNavController().navigate(action)
            }, 500)
        })
    }
}