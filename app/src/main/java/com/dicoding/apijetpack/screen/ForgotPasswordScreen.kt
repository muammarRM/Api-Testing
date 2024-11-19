package com.dicoding.apijetpack.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dicoding.apijetpack.viewmodel.AuthViewModel

@Composable
fun ForgotPasswordScreen(onNavigateToLogin: () -> Unit, viewModel: AuthViewModel = viewModel()) {
    var email by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    Column {
        TextField(value = email, onValueChange = { email = it }, label = { Text("Email") })

        Button(onClick = {
            viewModel.forgotPassword(email, onSuccess = {
                message = it
            }, onError = {
                message = it
            })
        }) {
            Text("Send Reset Link")
        }

        Button(onClick = onNavigateToLogin) {
            Text("Back to Login")
        }

        Text(message)
    }
}
