package screens.guest.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.joao.simsschool.R
import components.input.Input
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_login_screen.*

class LoginScreen : Fragment() {
//    var props = LoginScreenModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState);

        val view = inflater.inflate(R.layout.fragment_login_screen, container, false)
        val email = childFragmentManager.findFragmentById(R.id.fragment_login_screen_input_email)!! as Input
        val password = childFragmentManager.findFragmentById(R.id.fragment_login_screen_input_password)!! as Input

//        email.setProps(props.form.inputs[0])
//        password.setProps(props.form.inputs[1])

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button = fragment_login_screen_button_submit
    }

    override fun onDestroyView() {
        super.onDestroyView()
        this.clearFindViewByIdCache()
    }
}