// navigation/AppNavigation.kt
package com.example.quanlythuvien.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import com.example.quanlythuvien.screens.QuanLyScreen
import com.example.quanlythuvien.screens.SachScreen // <-- IMPORT MỚI
import com.example.quanlythuvien.screens.SinhVienScreen
import com.example.quanlythuvien.viewmodel.SharedViewModel

// 1. Định nghĩa các Route
sealed class Screen(val route: String, val title: String) {
    object QuanLy : Screen("quanly", "Quản lý")
    object DSSach : Screen("dssach", "DS Sách")
    object SinhVien : Screen("sinhvien", "Sinh viên")
}

val bottomNavItems = listOf(Screen.QuanLy, Screen.DSSach, Screen.SinhVien)

// 2. Composable điều hướng chính
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val sharedViewModel: SharedViewModel = viewModel()

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                bottomNavItems.forEach { screen ->
                    NavigationBarItem(
                        selected = currentDestination?.route == screen.route,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.startDestinationId)
                                launchSingleTop = true
                            }
                        },
                        icon = { /* Thêm icon nếu muốn */ },
                        label = { Text(screen.title) }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.QuanLy.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.QuanLy.route) {
                QuanLyScreen(
                    viewModel = sharedViewModel,
                    onNavigateToStudents = { navController.navigate(Screen.SinhVien.route) },
                    onNavigateToBooks = { navController.navigate(Screen.DSSach.route) }
                )
            }

            // --- DÒNG NÀY ĐÃ ĐƯỢC THAY THẾ ---
            composable(Screen.DSSach.route) {
                SachScreen(
                    viewModel = sharedViewModel,
                    onBookSelected = { navController.popBackStack() } // Quay lại màn hình trước
                )
            }

            composable(Screen.SinhVien.route) {
                SinhVienScreen(
                    viewModel = sharedViewModel,
                    onStudentSelected = { navController.popBackStack() } // Quay lại màn hình trước
                )
            }
        }
    }
}