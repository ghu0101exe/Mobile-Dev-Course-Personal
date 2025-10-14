package com.example.allbasicui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.allbasicui.components.GeneralTopAppBar
import com.example.allbasicui.navigation.Routes
import com.example.allbasicui.ui.theme.DangerColor
import com.example.allbasicui.ui.theme.DangerTextColor
import com.example.allbasicui.ui.theme.LightBlue

data class ComponentInfo(
    val title: String,
    val description: String,
    val route: String? = null
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComponentListScreen(navController: NavController) {
    val components = listOf(
        "Display" to listOf(
            ComponentInfo("Text", "Displays text", Routes.TEXT_DETAIL),
            ComponentInfo("Image", "Displays an image", Routes.IMAGE)
        ),
        "Input" to listOf(
            ComponentInfo("TextField", "Input field for text", Routes.TEXT_FIELD),
            ComponentInfo("PasswordField", "Input field for passwords", Routes.PASSWORD_FIELD)
        ),
        "Layout" to listOf(
            ComponentInfo("Column", "Arranges elements vertically", Routes.COLUMN_LAYOUT),
            ComponentInfo("Row", "Arranges elements horizontally", Routes.ROW_LAYOUT)
        )
    )

    val selfStudyComponent = ComponentInfo(
        "Tự tìm hiểu",
        "Tìm ra tất cả các thành phần UI Cơ bản"
    )

    Scaffold(
        topBar = { GeneralTopAppBar(title = "UI Components List", navController = navController) }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            components.forEach { (category, items) ->
                item {
                    Text(
                        text = category,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }
                items(items) { component ->
                    ComponentListItem(component = component, onClick = {
                        component.route?.let { navController.navigate(it) }
                    })
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                ComponentListItem(
                    component = selfStudyComponent,
                    isDanger = true,
                    onClick = {} // No action
                )
            }
        }
    }
}

@Composable
fun ComponentListItem(
    component: ComponentInfo,
    isDanger: Boolean = false,
    onClick: () -> Unit
) {
    val backgroundColor = if (isDanger) DangerColor else LightBlue
    val titleColor = if (isDanger) DangerTextColor else Color.Black
    val descriptionColor = if (isDanger) DangerTextColor else Color.Gray

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(backgroundColor)
            .clickable(onClick = onClick)
            .padding(16.dp)
    ) {
        Column {
            Text(
                text = component.title,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = titleColor
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = component.description,
                fontSize = 14.sp,
                color = descriptionColor
            )
        }
    }
}