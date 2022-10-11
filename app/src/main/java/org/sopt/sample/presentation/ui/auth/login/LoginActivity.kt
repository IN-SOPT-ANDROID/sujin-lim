package org.sopt.sample.presentation.ui.auth.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import org.sopt.sample.R
import org.sopt.sample.databinding.ActivityLoginBinding
import org.sopt.sample.domain.model.auth.TextInput
import org.sopt.sample.presentation.common.base.BindingActivity
import org.sopt.sample.presentation.common.extension.showSnackbar
import org.sopt.sample.presentation.common.extension.showToast
import org.sopt.sample.presentation.ui.auth.signup.SignUpActivity
import org.sopt.sample.presentation.ui.introduce.IntroduceActivity
import timber.log.Timber

class LoginActivity : BindingActivity<ActivityLoginBinding>(R.layout.activity_login) {
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initLauncher()
        bindingView()
    }

    private fun initLauncher() {
        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                val result = it.data
                with(viewModel) {
                    userId = result?.getStringExtra(USER_ID_KEY).toString()
                    userPw = result?.getStringExtra(USER_PW_KEY).toString()
                    userMbti = result?.getStringExtra(USER_MBTI_KEY).toString()
                    Timber.tag(TAG).e(userId)
                    Timber.tag(TAG).e(userPw)
                    Timber.tag(TAG).e(userMbti)
                }
            }
        }
    }

    private fun bindingView() {
        binding.activity = this
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
    }

    private fun observeData() {
        with(viewModel) {
            curId = binding.layoutIdTextInput.etTextInput.text.toString()
            curPw = binding.layoutPwTextInput.etTextInput.text.toString()
        }
    }

    fun login() {
        observeData()
        if(viewModel.isSuccessLogin()) {
            showToast(message = getString(R.string.login_success_text))
            navigateToMain()
        }
        else {
            binding.root.showSnackbar(message = getString(R.string.login_error_text))
        }
    }

    private fun navigateToMain() {
        val intent = Intent(this, IntroduceActivity::class.java).apply {
            putExtra(USER_ID_KEY, viewModel.userId)
            putExtra(USER_PW_KEY, viewModel.userPw)
            putExtra(USER_MBTI_KEY, viewModel.userMbti)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        startActivity(intent)
    }

    fun navigateToSignUp() {
        val intent = Intent(this, SignUpActivity::class.java)
        resultLauncher.launch(intent)
    }

    companion object {
        private const val TAG = "LoginActivity"
        const val USER_ID_KEY = "userId"
        const val USER_PW_KEY = "userPw"
        const val USER_MBTI_KEY = "userMbti"
    }
}