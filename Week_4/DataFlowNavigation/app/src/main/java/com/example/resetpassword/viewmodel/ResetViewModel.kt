// viewmodel/ResetViewModel.kt
package com.example.resetpassword.viewmodel

import android.util.Patterns
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

// Lớp chứa toàn bộ trạng thái
data class ResetUiState(
    val email: String = "",
    val otp: String = "",
    val newPassword: String = "",
    val confirmPassword: String = "",
    val isEmailValid: Boolean = false,
    val isOtpValid: Boolean = false,
    val doPasswordsMatch: Boolean = false
)

class ResetViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ResetUiState())
    val uiState = _uiState.asStateFlow()

    private val correctOtp = "11205" // Mã OTP mặc định
    // Regex chỉ chấp nhận ...@gmail.com
    private val gmailRegex = Regex("^[A-Za-z0-9._%+-]+@gmail\\.com$")

    fun onEmailChange(email: String) {
        _uiState.update {
            it.copy(
                email = email,
                isEmailValid = gmailRegex.matches(email) // Kiểm tra định dạng Gmail
            )
        }
    }

    // Yêu cầu của bạn là 5 số, nên chúng ta sẽ dùng 1 string 5 ký tự
    fun onOtpChange(otp: String) {
        if (otp.length <= 5) {
            _uiState.update {
                it.copy(
                    otp = otp,
                    isOtpValid = (otp == correctOtp)
                )
            }
        }
    }

    fun onNewPasswordChange(pass: String) {
        _uiState.update {
            it.copy(
                newPassword = pass,
                doPasswordsMatch = (pass.isNotEmpty() && pass == it.confirmPassword)
            )
        }
    }

    fun onConfirmPasswordChange(pass: String) {
        _uiState.update {
            it.copy(
                confirmPassword = pass,
                doPasswordsMatch = (pass.isNotEmpty() && pass == it.newPassword)
            )
        }
    }
}