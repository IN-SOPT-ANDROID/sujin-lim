package org.sopt.sample.presentation.ui.auth.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.google.android.material.snackbar.Snackbar
import org.sopt.sample.R
import org.sopt.sample.databinding.ActivityLoginBinding
import org.sopt.sample.presentation.common.base.BaseActivity
import org.sopt.sample.presentation.ui.auth.signup.SignUpActivity

class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {
    private var isIdSuccess: Boolean = false
    private var isPwSuccess: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        initListener()
    }

    private fun initView() {
        binding.layoutIdTextInput.apply {
            tvTextInputSign.text = getString(R.string.id_sign_text)
            tiTextInput.hint = getString(R.string.id_hint_text)
        }

        binding.layoutPwTextInput.apply {
            tvTextInputSign.text = getString(R.string.pw_sign_text)
            tiTextInput.hint = getString(R.string.pw_hint_text)
        }
    }

    private fun initListener() {

        binding.layoutIdTextInput.etTextInput.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                p0?.let {
                    isIdSuccess = it.length >= MIN_LENGTH
                }
            }
            override fun afterTextChanged(p0: Editable?) {}
        })

        binding.layoutPwTextInput.etTextInput.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                p0?.let {
                    isPwSuccess = it.length in MIN_LENGTH .. MAX_LENGTH
                }
            }
            override fun afterTextChanged(p0: Editable?) {}
        })

        binding.btnLogin.setOnClickListener {
            checkLogin()
        }

        binding.btnSignUp.setOnClickListener {
            navigateToSignUp()
        }
    }

    private fun navigateToSignUp() {
        val intent = Intent(this, SignUpActivity::class.java)
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

    private fun checkLogin() {
        if (isIdSuccess && !isPwSuccess) {
            showSnackBar(
                view = binding.layoutPwTextInput.tiTextInput,
                message = getString(R.string.login_pw_error_text)
            )
        }
        else {
            if (!isIdSuccess) {
                showSnackBar(
                    view = binding.layoutIdTextInput.tiTextInput,
                    message = getString(R.string.login_id_error_text)
                )
            }
            else {
                showSnackBar(
                    view = binding.root,
                    message = getString(R.string.login_success_text)
                )
            }
        }
    }

    companion object {
        private const val MIN_LENGTH = 6
        private const val MAX_LENGTH = 10
    }
}