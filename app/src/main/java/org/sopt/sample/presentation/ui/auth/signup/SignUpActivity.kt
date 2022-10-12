package org.sopt.sample.presentation.ui.auth.signup

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import org.sopt.sample.R
import org.sopt.sample.databinding.ActivitySignUpBinding
import org.sopt.sample.domain.model.auth.TextInputGuide
import org.sopt.sample.presentation.common.base.BindingActivity
import org.sopt.sample.presentation.common.extension.showSnackbar
import org.sopt.sample.presentation.ui.auth.login.LoginActivity

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
    }

    fun signUp() {
        observeData()
        if(viewModel.isSignUpSuccess()) {
            binding.root.showSnackbar(getString(R.string.signup_success_text))
            navigateToLogin()
        }
        else {
            binding.root.showSnackbar(getString(R.string.signup_error_text))
        }
    }

    private fun observeData() {
        with(viewModel) {
            curId = binding.layoutIdTextInput.etTextInput.text.toString()
            curPw = binding.layoutPwTextInput.etTextInput.text.toString()
            curMbti = binding.layoutMbtiTextInput.etTextInput.text.toString()
        }
    }

    private fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java).apply {
            putExtra(LoginActivity.USER_ID_KEY, viewModel.curId)
            putExtra(LoginActivity.USER_PW_KEY, viewModel.curPw)
            putExtra(LoginActivity.USER_MBTI_KEY, viewModel.curMbti)
        }
        setResult(RESULT_OK, intent)
        if (!isFinishing) finish()
    }

    companion object {
        private const val TAG = "SignUpActivity"
    }
}