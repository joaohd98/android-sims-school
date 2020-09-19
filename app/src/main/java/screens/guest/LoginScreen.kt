package screens.guest

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.joao.simsschool.R
import com.joao.simsschool.databinding.ScreenLoginBinding
import repositories.RepositoryStatus
import utils.setLoading

class LoginScreen : Fragment() {
    private lateinit var binding: ScreenLoginBinding
    private val viewModel: LoginScreenViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

         binding = DataBindingUtil.inflate(
             inflater, R.layout.screen_login, container, false
         )

        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.hasTriedSubmitEmailInvalid.observe(viewLifecycleOwner, {
            viewModel.changedHasTriedSubmit(it, binding.fragmentLoginScreenInputEmail)
        })

        viewModel.hasTriedSubmitPasswordInvalid.observe(viewLifecycleOwner,  {
            viewModel.changedHasTriedSubmit(it, binding.fragmentLoginScreenInputPassword)
        })

        val buttonSubmit = binding.fragmentLoginScreenButtonSubmit

        viewModel.status.observe(viewLifecycleOwner,  {
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
                    this.findNavController().navigate(R.id.action_loginScreen_to_tabsFragment)
                }
                else -> {}
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.fragmentLoginScreenButtonSubmit.dispose()
    }
}