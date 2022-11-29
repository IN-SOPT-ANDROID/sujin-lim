package org.sopt.sample.presentation.ui.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.sopt.sample.data.remote.api.ApiPool
import org.sopt.sample.data.remote.model.request.auth.RequestLoginDTO
import org.sopt.sample.data.remote.model.response.auth.ResponseAuthDTO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {
    private val _userId: MutableLiveData<String> = MutableLiveData("")
    val userId: LiveData<String>
        get() = _userId


    private val _userPw: MutableLiveData<String> = MutableLiveData("")
    val userPw: LiveData<String>
        get() = _userPw


    private val _userMbti: MutableLiveData<String> = MutableLiveData("")
    val userMbti: LiveData<String>
        get() = _userMbti

    /** login 결과로 가져온 user result **/
    private val _loginResult: MutableLiveData<ResponseAuthDTO> = MutableLiveData()
    val loginResult: LiveData<ResponseAuthDTO>
        get() = _loginResult

    fun setUserAccount (userId: String, userPw: String) {
        _userId.value = userId
        _userPw.value = userPw
    }

    fun initUserInfo (userId: String, userPw: String, userMbti: String) {
        _userId.value = userId
        _userPw.value = userPw
        _userMbti.value = userMbti
    }

    fun login() {
        ApiPool.authApi.login(
            request = RequestLoginDTO(
                email = _userId.value!!,
                password = _userPw.value!!
            )
        ).enqueue(object :
            Callback<ResponseAuthDTO> {
            override fun onResponse(
                call: Call<ResponseAuthDTO>,
                response: Response<ResponseAuthDTO>
            ) {
                if (response.isSuccessful) {
                    _loginResult.value = response.body()
                }
            }

            override fun onFailure(call: Call<ResponseAuthDTO>, t: Throwable) {
                // TODO : error handling
                // binding.root.showSnackbar(message = getString(R.string.login_error_text))
            }
        })
    }

    // saved user info
//    var userId: String = ""
//    var userPw: String = ""
//    var userMbti: String = ""

    // user input info
//    var curId: String = ""
//    var curPw: String = ""
//
//    fun isSuccessLogin(): Boolean =
//        (userId.isNotEmpty() && userPw.isNotEmpty())
//                && (curId.isNotEmpty() && curPw.isNotEmpty())
//                && (userId == curId && userPw == curPw)
}