package com.example.kbtour.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.kbtour.ui.theme.TextBlue

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigationExample(){
//    val backStackEntry = navController.currentBackStackEntryAsState()

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = TextBlue,
                modifier = Modifier.height(70.dp)
            ) {
                bottomNavItems.forEach { item ->
//                    val selected = item.route == backStackEntry.value?.destination?.route

                    NavigationBarItem(
                        selected = false,

                        onClick = {
//                            navController.navigate(item.route)
                                  },
                        label = {
                            Text(
                                text = item.name,
                                fontWeight = FontWeight.SemiBold,
                            )
                        },
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = "${item.name} Icon",
                            )
                        }
                    )
                }
            }
        },
        content = {
            //Your UI Content
        }
    )
}


data class BottomNavItem(
    val name: String,
    val route: String,
    val icon: ImageVector,
)

val bottomNavItems = listOf(
    BottomNavItem(
        name = "Home",
        route = "home",
        icon = Icons.Rounded.Home,
    ),
    BottomNavItem(
        name = "Create",
        route = "add",
        icon = Icons.Rounded.AddCircle,
    ),
    BottomNavItem(
        name = "Settings",
        route = "settings",
        icon = Icons.Rounded.Settings,
    ),
)


