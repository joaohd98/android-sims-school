package screens.guest.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.joao.simsschool.R
import com.joao.simsschool.databinding.FragmentLoginScreenBinding
import kotlinx.android.synthetic.main.fragment_login_screen.*
import screens.guest.login.view_model.LoginScreenViewModel

class LoginScreen : Fragment() {
    private val viewModel: LoginScreenViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val binding: FragmentLoginScreenBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_login_screen, container, false
        )

        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.hasTriedSubmitEmailInvalid.observe(viewLifecycleOwner, Observer {
            viewModel.changedHasTriedSubmit(it, fragment_login_screen_input_email)
        })

        viewModel.hasTriedSubmitPasswordInvalid.observe(viewLifecycleOwner, Observer {
            viewModel.changedHasTriedSubmit(it, fragment_login_screen_input_password)
        })
    }


}