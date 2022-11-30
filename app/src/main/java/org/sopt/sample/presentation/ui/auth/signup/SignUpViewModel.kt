package org.sopt.sample.presentation.ui.auth.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.sample.data.remote.model.request.auth.RequestSignupDto
import org.sopt.sample.data.remote.model.response.auth.ResponseAuthDto
import org.sopt.sample.data.remote.repository.RemoteRepository
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val remoteRepository: RemoteRepository
) : ViewModel() {
    val userId = MutableLiveData<String>()
    val userPw = MutableLiveData<String>()
    val userName = MutableLiveData<String>()
    val userMbti = MutableLiveData<String>()

    /** signup 결과로 가져온 user result **/
    private val _signupResult: MutableLiveData<ResponseAuthDto> = MutableLiveData()
    val signupResult: LiveData<ResponseAuthDto>
        get() = _signupResult

    fun signUp() {
        viewModelScope.launch {
            kotlin.runCatching {
                remoteRepository.signup(
                    RequestSignupDto(
                        email = userId.value.toString(),
                        password = userPw.value.toString(),
                        name = userName.value.toString()
                    )
                )
            }
                .onSuccess {
                    _signupResult.value = it
                }
                .onFailure {
                    Timber.e(it)
                }
        }
    }

    fun isMatchSignUpInput(): Boolean {
        if (userId.value.isNullOrBlank() || userPw.value.isNullOrBlank() || userName.value.isNullOrBlank())
            return false

        return userId.value!!.length in ID_MIN_LENGTH..ID_MAX_LENGTH &&
                userPw.value!!.length in PW_MIN_LENGTH..PW_MAX_LENGTH &&
                userName.value!!.isNotBlank()
    }

    companion object {
        private const val TAG = "SignUpViewModel"
        private const val ID_MIN_LENGTH = 6
        private const val ID_MAX_LENGTH = 10
        private const val PW_MIN_LENGTH = 8
        private const val PW_MAX_LENGTH = 12
    }
}