package screens.guest.login

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import br.com.simplepass.loadingbutton.customViews.CircularProgressButton
import com.joao.simsschool.R
import com.joao.simsschool.databinding.FragmentLoginScreenBinding
import kotlinx.android.synthetic.main.fragment_login_screen.*
import repositories.RepositoryStatus
import utils.setLoading

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

        val buttonSubmit = fragment_login_screen_button_submit as CircularProgressButton

        viewModel.status.observe(viewLifecycleOwner, Observer {
            when (it) {
                RepositoryStatus.FAILED -> {
                    view.setLoading(true)
                    buttonSubmit.revertAnimation()

                    activity?.let { activity ->
                        val builder = AlertDialog.Builder(activity)
                        builder.apply {
                            setTitle("There is something wrong")
                            setMessage(viewModel.statusMessage.value)
                            setPositiveButton(R.string.ok) { _, _ -> }
                        }
                        builder.create().show()
                    }
                }
                RepositoryStatus.LOADING -> {
                    view.setLoading(false)
                    buttonSubmit.startAnimation()
                }
                RepositoryStatus.SUCCESS -> {
                    this.findNavController().navigate(R.id.action_loginScreen_to_nav_graph_logged)
                }
                else -> {}
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()

        val buttonSubmit = fragment_login_screen_button_submit as CircularProgressButton
        buttonSubmit.dispose()
    }


}