package com.example.allbasicui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.allbasicui.components.GeneralTopAppBar

@Composable
fun TextDetailScreen(navController: NavController) {
    Scaffold(
        topBar = { GeneralTopAppBar(title = "Text Detail", navController = navController) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                textAlign = TextAlign.Start,
                lineHeight = 40.sp,
                fontSize = 28.sp, // Kích thước font chữ cơ bản
                text = buildAnnotatedString {
                    // "The" - Normal
                    append("The ")

                    // "quick" - Gạch ngang (strikethrough)
                    withStyle(
                        style = SpanStyle(textDecoration = TextDecoration.LineThrough)
                    ) {
                        append("quick")
                    }

                    append(" ") // Khoảng trắng

                    // "Brown" - Bold, màu nâu, và to hơn
                    withStyle(
                        style = SpanStyle(
                            color = Color(0xFFC07621),
                            fontWeight = FontWeight.Bold,
                            fontSize = 32.sp
                        )
                    ) {
                        append("Brown")
                    }

                    // Xuống dòng và thêm "fox"
                    append("\nfox ")

                    // "j u m p s" - Tăng khoảng cách ký tự
                    withStyle(
                        style = SpanStyle(letterSpacing = 8.sp)
                    ) {
                        append("j u m p s ")
                    }

                    // "over" - Bold
                    withStyle(
                        style = SpanStyle(fontWeight = FontWeight.Bold)
                    ) {
                        append("over")
                    }

                    // Xuống dòng
                    append("\n")

                    // "the" - Gạch chân
                    withStyle(
                        style = SpanStyle(textDecoration = TextDecoration.Underline)
                    ) {
                        append("the")
                    }

                    append(" ") // Khoảng trắng

                    // "lazy" - In nghiêng
                    withStyle(
                        style = SpanStyle(fontStyle = FontStyle.Italic)
                    ) {
                        append("lazy")
                    }

                    // "dog." - Normal
                    append(" dog.")
                }
            )
        }
    }
}