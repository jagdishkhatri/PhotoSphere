package com.site2apps.photosphere.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.site2apps.photosphere.isValidAge
import com.site2apps.photosphere.isValidEmail
import com.site2apps.photosphere.isValidPassword

class RegisterViewModel : ViewModel() {

    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val age = MutableLiveData<String>()

    private val _emailError = MutableLiveData<String?>()
    val emailError: LiveData<String?> get() = _emailError

    private val _passwordError = MutableLiveData<String?>()
    val passwordError: LiveData<String?> get() = _passwordError

    private val _ageError = MutableLiveData<String?>()
    val ageError: LiveData<String?> get() = _ageError

    private val _isRegisterValid = MutableLiveData<Boolean?>()
    val isRegisterValid: LiveData<Boolean?> get() = _isRegisterValid

    fun validateAndRegister() {
        val email = email.value.orEmpty()
        val password = password.value.orEmpty()
        val age = age.value?.toIntOrNull()

        if (!isValidEmail(email)) {
            _emailError.value = "Invalid Email"
            return
        } else {
            _emailError.value = null
        }

        if (!isValidPassword(password)) {
            _passwordError.value = "Password must be between 6 and 12 characters"
            return
        } else {
            _passwordError.value = null
        }

        if (age == null || !isValidAge(age)) {
            _ageError.value = "Age must be between 18 and 99"
            return
        } else {
            _ageError.value = null
        }

        // Simulate a successful registration
        _isRegisterValid.value = true
    }
}
