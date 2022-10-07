package org.sopt.sample.presentation.ui.auth.signup

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import org.sopt.sample.R
import org.sopt.sample.databinding.ActivitySignUpBinding
import org.sopt.sample.domain.model.auth.TextInput
import org.sopt.sample.presentation.common.base.BaseActivity
import org.sopt.sample.presentation.ui.auth.login.LoginActivity

class SignUpActivity : BaseActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initListener()
    }

    private fun initView() {
        binding.layoutIdTextInput.textInput =
            TextInput(
                sign = getString(R.string.id_sign_text),
                hint = getString(R.string.id_hint_text),
                isPassword = false
            )

        binding.layoutPwTextInput.textInput =
            TextInput(
                sign = getString(R.string.pw_sign_text),
                hint = getString(R.string.pw_hint_text),
                isPassword = true
            )

        binding.layoutMbtiTextInput.textInput =
            TextInput(
                sign = getString(R.string.mbti_sign_text),
                hint = getString(R.string.mbti_hint_text),
                isPassword = false
            )
    }

    private fun initListener() {

    }

    private fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    private fun showSnackBar(view: View, message: String) {
        Snackbar.make(
            binding.root,
            message,
            Snackbar.LENGTH_SHORT
        ).apply {
            if(view != binding.root)
                anchorView = view
        }.show()
    }
}