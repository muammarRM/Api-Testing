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
fun LoginScreen(
    onNavigateToRegister: () -> Unit,
    onNavigateToForgotPassword: () -> Unit,
    viewModel: AuthViewModel = viewModel()
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    Column {
        TextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
        TextField(value = password, onValueChange = { password = it }, label = { Text("Password") })

        Button(onClick = {
            viewModel.loginUser(email, password, onSuccess = {
                message = "Login Successful! Token: ${it.token}"
            }, onError = {
                message = it
            })
        }) {
            Text("Login")
        }

        Button(onClick = onNavigateToRegister) {
            Text("Create New Account")
        }

        Button(onClick = onNavigateToForgotPassword) {
            Text("Forgot Password?")
        }

        Text(message)
    }
}
