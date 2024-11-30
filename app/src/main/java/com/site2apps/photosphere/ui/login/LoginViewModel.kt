package com.site2apps.photosphere.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.site2apps.photosphere.isValidEmail
import com.site2apps.photosphere.isValidPassword

class LoginViewModel : ViewModel() {
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    private val _emailError = MutableLiveData<String?>()
    val emailError: LiveData<String?> get() = _emailError

    private val _passwordError = MutableLiveData<String?>()
    val passwordError: LiveData<String?> get() = _passwordError

    private val _isLoginValid = MutableLiveData<Boolean>()
    val isLoginValid: LiveData<Boolean> get() = _isLoginValid

    fun validateAndLogin() {
        val emailInput = email.value.orEmpty()
        val passwordInput = password.value.orEmpty()

        var isValid = true

        // Email Validation
        if (!isValidEmail(emailInput)) {
            _emailError.value = "Invalid email"
            isValid = false
        } else {
            _emailError.value = null
        }

        // Password Validation
        if (!isValidPassword(passwordInput)) {
            _passwordError.value = "Password must be 6-12 characters long"
            isValid = false
        } else {
            _passwordError.value = null
        }

        _isLoginValid.value = isValid
    }
}
