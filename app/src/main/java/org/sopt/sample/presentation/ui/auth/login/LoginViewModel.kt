package org.sopt.sample.presentation.ui.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.sample.data.remote.model.request.auth.RequestLoginDto
import org.sopt.sample.data.remote.model.request.auth.RequestSignupDto
import org.sopt.sample.data.remote.model.response.auth.ResponseAuthDto
import org.sopt.sample.data.remote.repository.RemoteRepository
import org.sopt.sample.presentation.state.UiState
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val remoteRepository: RemoteRepository
) : ViewModel() {
    val userId = MutableLiveData<String>()
    val userPw = MutableLiveData<String>()

    private val _loginState: MutableLiveData<UiState<ResponseAuthDto>> =
        MutableLiveData(UiState.Init)
    val loginState: LiveData<UiState<ResponseAuthDto>>
        get() = _loginState

//    private val _userId: MutableLiveData<String> = MutableLiveData("")
//    val userId: LiveData<String>
//        get() = _userId
//
//
//    private val _userPw: MutableLiveData<String> = MutableLiveData("")
//    val userPw: LiveData<String>
//        get() = _userPw
//
//
//    private val _userMbti: MutableLiveData<String> = MutableLiveData("")
//    val userMbti: LiveData<String>
//        get() = _userMbti


//    fun setUserAccount(userId: String, userPw: String) {
//        _userId.value = userId
//        _userPw.value = userPw
//    }
//
//    fun initUserInfo(userId: String, userPw: String, userMbti: String) {
//        _userId.value = userId
//        _userPw.value = userPw
//        _userMbti.value = userMbti
//    }

    fun login() {
        viewModelScope.launch {
            _loginState.value = UiState.Loading(true)

            runCatching {
                remoteRepository.login(RequestLoginDto(
                    email = userId.value.toString(),
                    password = userPw.value.toString()
                ))
            }
                .onSuccess {
                    _loginState.value = UiState.Loading(false)
                    _loginState.value = UiState.Success(it)
                }
                .onFailure {
                    _loginState.value = UiState.Loading(false)
                    _loginState.value = UiState.Error(it.toString())
                }
        }
    }
}