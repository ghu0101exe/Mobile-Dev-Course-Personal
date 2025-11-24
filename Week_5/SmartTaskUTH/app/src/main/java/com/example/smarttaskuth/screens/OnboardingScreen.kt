package com.example.smarttaskuth.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.smarttaskuth.R
import com.example.smarttaskuth.navigation.Screen
import kotlinx.coroutines.launch

data class OnboardingItem(
    val image: Int,
    val title: String,
    val description: String
)

@Composable
fun getOnboardingItems(): List<OnboardingItem> {
    return listOf(
        OnboardingItem(
            R.drawable.onboarding_image_1,
            "Easy Time Management",
            "With management based on priority and daily tasks, it will give you convenience in managing and determining the tasks that must be done first"
        ),
        OnboardingItem(
            R.drawable.onboarding_image_2,
            "Increase Work Effectiveness",
            "Time management and the determination of more important tasks will give your job statistics better and always improve"
        ),
        OnboardingItem(
            R.drawable.onboarding_image_3,
            "Reminder Notification",
            "The advantage of this application is that it also provides reminders for you so you don't forget to keep doing your assignments well and according to the time you have set"
        )
    )
}

@Composable
fun OnboardingScreen(navController: NavController) {
    val context = LocalContext.current
    val items = getOnboardingItems()
    val pagerState = rememberPagerState(pageCount = { items.size })
    val scope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize()) {
        TextButton(
            onClick = { navigateToLogin(navController, context) },
            modifier = Modifier
                .align(Alignment.End)
                .padding(16.dp)
        ) {
            val isLastPage = pagerState.currentPage == items.size - 1
            if (!isLastPage) {
                Text(text = "skip", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            pageSize = PageSize.Fill
        ) { page ->
            OnboardingPage(item = items[page])
        }

        Row(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(items.size) { iteration ->
                val color = if (pagerState.currentPage == iteration) Color(0xFF3F51B5) else Color.LightGray
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .clip(CircleShape)
                        .size(10.dp)
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 32.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val isFirstPage = pagerState.currentPage == 0
            val isLastPage = pagerState.currentPage == items.size - 1

            if (!isFirstPage) {
                IconButton(onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage - 1)
                    }
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        modifier = Modifier.size(32.dp),
                        tint = Color(0xFF3F51B5)
                    )
                }
            } else {
                Spacer(modifier = Modifier.width(48.dp))
            }

            Button(
                onClick = {
                    if (isLastPage) {
                        navigateToLogin(navController, context)
                    } else {
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    }
                },
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp)
            ) {
                Text(
                    text = if (isLastPage) "Get Started" else "Next",
                    fontSize = 18.sp
                )
            }
        }
    }
}

@Composable
fun OnboardingPage(item: OnboardingItem) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Image(
            painter = painterResource(id = item.image),
            contentDescription = item.title,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.height(48.dp))
        Text(
            text = item.title,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = item.description,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            color = Color.Gray
        )
    }
}

private fun navigateToLogin(navController: NavController, context: Context) {
    val sharedPref = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    with(sharedPref.edit()) {
        putBoolean("hasSeenOnboarding", true)
        apply()
    }
    navController.navigate(Screen.Login.route) {
        popUpTo(Screen.Onboarding.route) {
            inclusive = true
        }
    }
}