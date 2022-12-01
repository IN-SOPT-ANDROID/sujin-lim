package org.sopt.sample.presentation.ui.auth.signup

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.sample.R
import org.sopt.sample.databinding.ActivitySignUpBinding
import org.sopt.sample.presentation.model.auth.TextInputGuide
import org.sopt.sample.presentation.common.binding.BindingActivity
import org.sopt.sample.presentation.common.extension.showSnackbar
import org.sopt.sample.presentation.common.extension.toGone
import org.sopt.sample.presentation.common.extension.toVisible
import org.sopt.sample.presentation.state.UiState
import org.sopt.sample.presentation.ui.auth.login.LoginActivity
import timber.log.Timber

@AndroidEntryPoint
class SignUpActivity : BindingActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {
    private val viewModel: SignUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingView()
        observeState()
    }

    private fun bindingView() {
        binding.activity = this
        binding.viewModel = viewModel

        binding.layoutIdTextInput.apply {
            textInputGuide = TextInputGuide(
                sign = getString(R.string.id_sign_text),
                hint = getString(R.string.id_hint_text),
                isPassword = false
            )
            textContent = viewModel.userId
        }

        binding.layoutPwTextInput.apply {
            textInputGuide = TextInputGuide(
                sign = getString(R.string.pw_sign_text),
                hint = getString(R.string.pw_hint_text),
                isPassword = true
            )
            textContent = viewModel.userPw
        }

        binding.layoutMbtiTextInput.apply {
            textInputGuide = TextInputGuide(
                sign = getString(R.string.mbti_sign_text),
                hint = getString(R.string.mbti_hint_text),
                isPassword = false
            )
            textContent = viewModel.userMbti
        }

        binding.layoutNameTextInput.apply {
            textInputGuide = TextInputGuide(
                sign = getString(R.string.name_sign_text),
                hint = getString(R.string.name_hint_text),
                isPassword = false
            )
            textContent = viewModel.userName
        }
    }

    fun signUp() {
        if (viewModel.isMatchSignUpInput()) {
            viewModel.signUp()
        } else {
            binding.root.showSnackbar(getString(R.string.signup_error_text))
        }
    }

    private fun observeState() {
        viewModel.signupState.observe(this) {
            when (it) {
                is UiState.Loading -> {
                    binding.pbSignupLoading.toVisible()
                }
                is UiState.Success -> {
                    binding.pbSignupLoading.toGone()
                    binding.root.showSnackbar(getString(R.string.signup_success_text))
                    navigateToLogin()
                }
                is UiState.Error -> {
                    binding.pbSignupLoading.toGone()
                    binding.root.showSnackbar(getString(R.string.signup_fail_text))
                }
                else -> {
                    binding.pbSignupLoading.toGone()
                }
            }
        }
    }

    private fun navigateToLogin() {
        // TODO : API 있으니깐 삭제 가능
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