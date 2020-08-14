package screens.guest.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.joao.simsschool.R
import com.joao.simsschool.databinding.FragmentLoginScreenBinding
import kotlinx.android.synthetic.*
import screens.guest.login.view_model.LoginScreenViewModel

class LoginScreen : Fragment() {
//    var props = LoginScreenModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val binding: FragmentLoginScreenBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_login_screen, container, false
        )

        binding.viewModel = ViewModelProvider(this).get(LoginScreenViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        this.clearFindViewByIdCache()
    }
}