package org.sopt.sample.presentation.ui.auth.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.google.android.material.snackbar.Snackbar
import org.sopt.sample.R
import org.sopt.sample.databinding.ActivityLoginBinding
import org.sopt.sample.domain.model.auth.TextInput
import org.sopt.sample.presentation.common.base.BaseActivity
import org.sopt.sample.presentation.ui.auth.signup.SignUpActivity
import org.sopt.sample.presentation.ui.main.MainActivity
import timber.log.Timber

class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initLauncher()
        initView()
        initListener()
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

    private fun observeData() {
        with(viewModel) {
            curId = binding.layoutIdTextInput.etTextInput.text.toString()
            curPw = binding.layoutPwTextInput.etTextInput.text.toString()
        }
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
    }

    private fun initListener() {
        binding.btnLoginSignup.setOnClickListener {
            navigateToSignUp()
        }

        binding.btnLogin.setOnClickListener {
            login()
        }
    }

    private fun login() {
        observeData()
        if(viewModel.isSuccessLogin()) {
            Toast.makeText(
                this,
                getString(R.string.login_success_text),
                Toast.LENGTH_SHORT
            ).show()

            navigateToMain()
        }
        else {
            showSnackBar(
                binding.root,
                getString(R.string.login_error_text)
            )
        }
    }

    private fun navigateToMain() {
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra(USER_ID_KEY, viewModel.userId)
            putExtra(USER_PW_KEY, viewModel.userPw)
            putExtra(USER_MBTI_KEY, viewModel.userMbti)
        }
        startActivity(intent)
    }

    private fun navigateToSignUp() {
        val intent = Intent(this, SignUpActivity::class.java)
        resultLauncher.launch(intent)
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

    companion object {
        private const val TAG = "LoginActivity"
        const val USER_ID_KEY = "userId"
        const val USER_PW_KEY = "userPw"
        const val USER_MBTI_KEY = "userMbti"
    }
}