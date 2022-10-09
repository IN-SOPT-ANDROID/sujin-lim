package org.sopt.sample.presentation.ui.auth.signup

import androidx.lifecycle.ViewModel

class SignUpViewModel: ViewModel() {

    var curId: String = ""
    var curPw: String = ""
    var curMbti: String = ""

    fun isSignUpSuccess(): Boolean {
        return (
            curId.length in ID_MIN_LENGTH..ID_MAX_LENGTH
            && curPw.length in PW_MIN_LENGTH..PW_MAX_LENGTH
        )
    }

//    private val _curId: MutableLiveData<String> = MutableLiveData("")
//    val curId: LiveData<String>
//        get() = _curId
//
//    private val _curPw: MutableLiveData<String> = MutableLiveData("")
//    val curPw: LiveData<String>
//        get() = _curPw
//
//    private val _curMbti: MutableLiveData<String> = MutableLiveData("")
//    val curMbti: LiveData<String>
//        get() = _curMbti
//
//    private val _isIdSuccess: MutableLiveData<Boolean> = MutableLiveData(false)
//    val isIdSuccess: LiveData<Boolean>
//        get() = _isIdSuccess
//
//    private val _isPwSuccess: MutableLiveData<Boolean> = MutableLiveData(false)
//    val isPwSuccess: LiveData<Boolean>
//        get() = _isPwSuccess
//
//    private val _isMbtiSuccess: MutableLiveData<Boolean> = MutableLiveData(false)
//    val isMbtiSuccess: LiveData<Boolean>
//        get() = _isMbtiSuccess

    companion object {
        private const val TAG = "SignUpViewModel"
        private const val ID_MIN_LENGTH = 6
        private const val ID_MAX_LENGTH = 10
        private const val PW_MIN_LENGTH = 8
        private const val PW_MAX_LENGTH = 12
    }
}