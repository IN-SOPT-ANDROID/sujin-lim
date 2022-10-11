package org.sopt.sample.presentation.ui.introduce

import android.os.Bundle
import androidx.activity.viewModels
import org.sopt.sample.R
import org.sopt.sample.databinding.ActivityIntroduceBinding
import org.sopt.sample.presentation.common.base.BaseActivity
import org.sopt.sample.presentation.ui.auth.login.LoginActivity
import timber.log.Timber

class IntroduceActivity : BaseActivity<ActivityIntroduceBinding>(R.layout.activity_introduce) {
    private val viewModel: IntroduceViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingView()
    }

    private fun bindingView() {
        with(viewModel) {
            userId = intent.getStringExtra(LoginActivity.USER_ID_KEY).toString()
            userPw = intent.getStringExtra(LoginActivity.USER_PW_KEY).toString()
            userMbti = intent.getStringExtra(LoginActivity.USER_MBTI_KEY).toString()

            Timber.tag(TAG).e(userId)
            Timber.tag(TAG).e(userPw)
            Timber.tag(TAG).e(userMbti)
        }
        binding.vm = viewModel
    }

    companion object {
        private const val TAG = "IntroduceActivity"
    }
}