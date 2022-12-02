package org.sopt.sample.presentation.ui.auth.login

import androidx.lifecycle.ViewModel
import org.sopt.sample.data.remote.api.ApiPool
import org.sopt.sample.data.remote.model.request.auth.RequestLoginDTO
import org.sopt.sample.data.remote.model.response.auth.ResponseAuthDTO
import org.sopt.sample.data.remote.model.response.home.ResponseUserDTO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {

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