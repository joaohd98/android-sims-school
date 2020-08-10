package screens.guest.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.joao.simsschool.R
import components.InputFragment
import kotlinx.android.synthetic.main.fragment_login_screen.*
import screens.guest.login.model.LoginScreenModel

class LoginScreenFragment : Fragment() {
    var props = LoginScreenModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val email = fragment_login_screen_input_email
        val password = fragment_login_screen_input_password
        val button = fragment_login_screen_button_submit

//        email.setInputProps(this.props.form[0])
//        password.setInputProps(this.props.form[1])

    }

}