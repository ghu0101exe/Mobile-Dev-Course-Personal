// navigation/AppNavigation.kt
package com.example.resetpassword.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.resetpassword.ui.screens.ConfirmScreen
import com.example.resetpassword.ui.screens.ForgotPasswordScreen
import com.example.resetpassword.ui.screens.ResetPasswordScreen
import com.example.resetpassword.ui.screens.VerificationScreen
import com.example.resetpassword.viewmodel.ResetViewModel

// Định nghĩa các Route
sealed class Screen(val route: String) {
    object ForgotPassword : Screen("forgot_password")
    object Verification : Screen("verification")
    object ResetPassword : Screen("reset_password")
    object Confirm : Screen("confirm")
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    // Tạo 1 ViewModel duy nhất và chia sẻ cho tất cả các màn hình
    val viewModel: ResetViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.ForgotPassword.route
    ) {
        composable(Screen.ForgotPassword.route) {
            ForgotPasswordScreen(
                viewModel = viewModel,
                onNextClicked = { navController.navigate(Screen.Verification.route) }
            )
        }

        composable(Screen.Verification.route) {
            VerificationScreen(
                viewModel = viewModel,
                onNextClicked = { navController.navigate(Screen.ResetPassword.route) },
                onBackClicked = { navController.popBackStack() }
            )
        }

        composable(Screen.ResetPassword.route) {
            ResetPasswordScreen(
                viewModel = viewModel,
                onNextClicked = { navController.navigate(Screen.Confirm.route) },
                onBackClicked = { navController.popBackStack() }
            )
        }

        composable(Screen.Confirm.route) {
            ConfirmScreen(
                viewModel = viewModel,
                onSummitClicked = {
                    // Tạm thời quay về màn hình đầu tiên
                    navController.popBackStack(Screen.ForgotPassword.route, inclusive = false)
                },
                onBackClicked = { navController.popBackStack() }
            )
        }
    }
}