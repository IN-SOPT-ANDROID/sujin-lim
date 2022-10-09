package org.sopt.sample.presentation.ui.auth.login

import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {

    // saved user info
    var userId: String = ""
    var userPw: String = ""
    var userMbti: String = ""

    // user input info
    var curId: String = ""
    var curPw: String = ""

    fun isSuccessLogin(): Boolean =
        (userId.isNotEmpty() && userPw.isNotEmpty())
                && (curId.isNotEmpty() && curPw.isNotEmpty())
                && (userId == curId && userPw == curPw)
}