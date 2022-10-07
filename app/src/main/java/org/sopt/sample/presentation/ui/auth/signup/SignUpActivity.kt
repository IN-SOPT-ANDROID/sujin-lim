package org.sopt.sample.presentation.ui.auth.signup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.google.android.material.snackbar.Snackbar
import org.sopt.sample.R
import org.sopt.sample.databinding.ActivitySignUpBinding
import org.sopt.sample.domain.model.auth.TextInput
import org.sopt.sample.presentation.common.base.BaseActivity
import org.sopt.sample.presentation.ui.auth.login.LoginActivity
import timber.log.Timber

class SignUpActivity : BaseActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {
    private val viewModel: SignUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingView()
    }

    private fun bindingView() {
        binding.activity = this
        binding.layoutIdTextInput.apply {
            textInput = TextInput(
                sign = getString(R.string.id_sign_text),
                hint = getString(R.string.id_hint_text),
                isPassword = false
            )
        }

        binding.layoutPwTextInput.apply {
            textInput = TextInput(
                sign = getString(R.string.pw_sign_text),
                hint = getString(R.string.pw_hint_text),
                isPassword = true
            )
        }

        binding.layoutMbtiTextInput.apply {
            textInput = TextInput(
                sign = getString(R.string.mbti_sign_text),
                hint = getString(R.string.mbti_hint_text),
                isPassword = false
            )
        }
    }

    fun signUp() {
        observeData()
        if(viewModel.isSignUpSuccess()) {
            showSnackBar(
                    view = binding.root,
                    message = getString(R.string.signup_success_text)
            )
            navigateToLogin()
        }
        else {
            showSnackBar(
                view = binding.root,
                message = getString(R.string.signup_error_text)
            )
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

    private fun showSnackBar(view: View, message: String) {
        Snackbar.make(
            binding.root,
            message,
            Snackbar.LENGTH_SHORT
        ).apply {
            if(view != binding.root) anchorView = view
        }.show()
    }

    companion object {
        private const val TAG = "SignUpActivity"
    }
}