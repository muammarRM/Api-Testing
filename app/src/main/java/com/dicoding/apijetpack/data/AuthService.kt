package com.dicoding.apijetpack.data

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("api/auth/register")
    fun register(@Body request: RegisterRequest): Call<AuthResponse>

    @POST("api/auth/login")
    fun login(@Body request: LoginRequest): Call<AuthResponse>

    @POST("api/auth/forgot-password")
    fun forgotPassword(@Body request: ForgotPasswordRequest): Call<BasicResponse>

    @POST("api/auth/reset-password")
    fun resetPassword(@Body request: ResetPasswordRequest): Call<BasicResponse>

}

// Request dan Response Models
data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String,
    val password_confirmation: String
)

data class LoginRequest(
    val email: String,
    val password: String
)

data class ForgotPasswordRequest(
    val email: String
)

data class AuthResponse(
    val token: String, // JWT atau token lainnya
    val user: User
)

data class User(
    val id: Int,
    val name: String,
    val email: String
)

data class BasicResponse(
    val message: String
)
data class ResetPasswordRequest(
    val token: String,
    val email: String,
    val password: String
)
