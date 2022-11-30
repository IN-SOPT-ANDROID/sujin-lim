package org.sopt.sample.presentation.ui.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.sample.data.remote.model.response.auth.ResponseAuthDto

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
    private val _loginResult: MutableLiveData<ResponseAuthDto> = MutableLiveData()
    val loginResult: LiveData<ResponseAuthDto>
        get() = _loginResult

    fun setUserAccount(userId: String, userPw: String) {
        _userId.value = userId
        _userPw.value = userPw
    }

    fun initUserInfo(userId: String, userPw: String, userMbti: String) {
        _userId.value = userId
        _userPw.value = userPw
        _userMbti.value = userMbti
    }

//    fun login() {
//        ApiPool.authApi.login(
//            request = RequestLoginDto(
//                email = _userId.value!!,
//                password = _userPw.value!!
//            )
//        ).enqueue(object :
//            Callback<ResponseAuthDto> {
//            override fun onResponse(
//                call: Call<ResponseAuthDto>,
//                response: Response<ResponseAuthDto>
//            ) {
//                if (response.isSuccessful) {
//                    _loginResult.value = response.body()
//                }
//            }
//
//            override fun onFailure(call: Call<ResponseAuthDto>, t: Throwable) {
//                // TODO : error handling
//                // binding.root.showSnackbar(message = getString(R.string.login_error_text))
//            }
//        })
//    }
}