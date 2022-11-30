package org.sopt.sample.presentation.ui.auth.signup

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.sample.R
import org.sopt.sample.databinding.ActivitySignUpBinding
import org.sopt.sample.presentation.model.auth.TextInputGuide
import org.sopt.sample.presentation.common.binding.BindingActivity
import org.sopt.sample.presentation.common.extension.showSnackbar
import org.sopt.sample.presentation.ui.auth.login.LoginActivity

@AndroidEntryPoint
class SignUpActivity : BindingActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {
    private val viewModel: SignUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingView()
    }

    private fun bindingView() {
        binding.activity = this
        binding.layoutIdTextInput.apply {
            textInputGuide = TextInputGuide(
                sign = getString(R.string.id_sign_text),
                hint = getString(R.string.id_hint_text),
                isPassword = false
            )
        }

        binding.layoutPwTextInput.apply {
            textInputGuide = TextInputGuide(
                sign = getString(R.string.pw_sign_text),
                hint = getString(R.string.pw_hint_text),
                isPassword = true
            )
        }

        binding.layoutMbtiTextInput.apply {
            textInputGuide = TextInputGuide(
                sign = getString(R.string.mbti_sign_text),
                hint = getString(R.string.mbti_hint_text),
                isPassword = false
            )
        }

        binding.layoutNameTextInput.apply {
            textInputGuide = TextInputGuide(
                sign = getString(R.string.name_sign_text),
                hint = getString(R.string.name_hint_text),
                isPassword = false
            )
        }
    }

    fun signUp() {
        setUserInfo()
        if (viewModel.isMatchSignUpInput()) {
            viewModel.signUp()
            navigateToLogin()
        }
        else {
            binding.root.showSnackbar(getString(R.string.signup_error_text))
        }
    }

    private fun setUserInfo() {
        with(viewModel) {
            userId.value = binding.layoutIdTextInput.etTextInput.text.toString()
            userPw.value = binding.layoutPwTextInput.etTextInput.text.toString()
            userName.value = binding.layoutNameTextInput.etTextInput.text.toString()
            userMbti.value = binding.layoutMbtiTextInput.etTextInput.text.toString()
        }
    }

    private fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java).apply {
            putExtra(LoginActivity.USER_ID_KEY, viewModel.userId.value)
            putExtra(LoginActivity.USER_PW_KEY, viewModel.userPw.value)
            putExtra(LoginActivity.USER_MBTI_KEY, viewModel.userName.value)
        }
        setResult(RESULT_OK, intent)
        if (!isFinishing) finish()
    }

    companion object {
        private const val TAG = "SignUpActivity"
    }
}