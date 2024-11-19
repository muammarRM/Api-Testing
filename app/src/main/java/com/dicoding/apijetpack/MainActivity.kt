package com.dicoding.apijetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.dicoding.apijetpack.screen.ForgotPasswordScreen
import com.dicoding.apijetpack.screen.LoginScreen
import com.dicoding.apijetpack.screen.RegisterScreen
import com.dicoding.apijetpack.screen.ResetPasswordScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "login") {
                composable("login") {
                    LoginScreen(
                        onNavigateToRegister = { navController.navigate("register") },
                        onNavigateToForgotPassword = { navController.navigate("forgot-password") }
                    )
                }
                composable("register") {
                    RegisterScreen(onNavigateToLogin = { navController.navigate("login") })
                }
                composable("forgot-password") {
                    ForgotPasswordScreen(onNavigateToLogin = { navController.navigate("login") })
                }
                composable(
                    "reset-password/{token}/{email}",
                    arguments = listOf(
                        navArgument("token") { type = NavType.StringType },
                        navArgument("email") { type = NavType.StringType }
                    ),
                    deepLinks = listOf(
                        navDeepLink { uriPattern = "http://168.138.164.252/api/auth/reset-password?token={token}&email={email}" }
                    )
                ) { backStackEntry ->
                    val token = backStackEntry.arguments?.getString("token") ?: ""
                    val email = backStackEntry.arguments?.getString("email") ?: ""
                    ResetPasswordScreen(
                        token = token,
                        email = email,
                        onNavigateToLogin = { navController.navigate("login") }
                    )
                }
            }
        }
    }
}
