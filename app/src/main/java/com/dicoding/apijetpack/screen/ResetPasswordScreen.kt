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
fun ResetPasswordScreen(
    token: String,
    email: String,
    onNavigateToLogin: () -> Unit,
    viewModel: AuthViewModel = viewModel()
) {
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    Column {
        Text("Reset Password for: $email")

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
                viewModel.resetPassword(
                    token = token,
                    email = email,
                    password = password,
                    onSuccess = {
                        message = "Password reset successful!"
                    },
                    onError = {
                        message = "Error: $it"
                    }
                )
            } else {
                message = "Passwords do not match!"
            }
        }) {
            Text("Reset Password")
        }

        Button(onClick = onNavigateToLogin) {
            Text("Back to Login")
        }

        if (message.isNotEmpty()) {
            Text(message)
        }
    }
}
