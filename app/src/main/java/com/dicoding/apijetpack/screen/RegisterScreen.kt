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
fun RegisterScreen(onNavigateToLogin: () -> Unit, viewModel: AuthViewModel = viewModel()) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    Column {
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") }
        )
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") }
        )
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") }
        )
        TextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Confirm Password") }
        )

        Button(onClick = {
            if (password == confirmPassword) {
                viewModel.registerUser(
                    name = name,
                    email = email,
                    password = password,
                    passwordConfirmation = confirmPassword,
                    onSuccess = {
                        message = "Registration Successful! Token: ${it.token}"
                    },
                    onError = {
                        message = "Error: $it"
                    }
                )
            } else {
                message = "Passwords do not match!"
            }
        }) {
            Text("Register")
        }

        Button(onClick = onNavigateToLogin) {
            Text("Already have an account? Login")
        }

        if (message.isNotEmpty()) {
            Text(message)
        }
    }
}
