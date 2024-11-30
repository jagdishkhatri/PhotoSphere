package com.site2apps.photosphere

import android.util.Patterns

fun isValidEmail(email: String): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

fun isValidPassword(password: String): Boolean {
    // Password should be at least 6 characters long
    return password.length in 6..12
}

fun isValidAge(age: Int): Boolean {
    // Age should be between 18 and 99
    return age in 18..99
}