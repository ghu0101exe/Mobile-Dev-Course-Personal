package com.example.allbasicui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.allbasicui.screens.*

// Định nghĩa các route cho màn hình
object Routes {
    const val WELCOME = "welcome"
    const val COMPONENT_LIST = "component_list"
    const val TEXT_DETAIL = "text_detail"
    const val IMAGE = "image"
    const val TEXT_FIELD = "text_field"
    const val PASSWORD_FIELD = "password_field"
    const val COLUMN_LAYOUT = "column_layout"
    const val ROW_LAYOUT = "row_layout"
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.WELCOME) {
        composable(Routes.WELCOME) { WelcomeScreen(navController) }
        composable(Routes.COMPONENT_LIST) { ComponentListScreen(navController) }
        composable(Routes.TEXT_DETAIL) { TextDetailScreen(navController) }
        composable(Routes.IMAGE) { ImageScreen(navController) }
        composable(Routes.TEXT_FIELD) { TextFieldScreen(navController) }
        composable(Routes.PASSWORD_FIELD) { PasswordFieldScreen(navController) }
        composable(Routes.COLUMN_LAYOUT) { ColumnLayoutScreen(navController) }
        composable(Routes.ROW_LAYOUT) { RowLayoutScreen(navController) }
    }
}