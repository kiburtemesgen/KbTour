//package com.example.kbtour.navigation
//
//import androidx.compose.runtime.Composable
//import androidx.navigation.NavType
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//import androidx.navigation.navArgument
//import com.example.kbtour.HomeScreen
//import com.example.kbtour.view.PlaceDetail
//
//@Composable
//fun NavigationView() {
//    val navController = rememberNavController()
//    NavHost(navController = navController, startDestination = "home") {
//        composable("home") {
//            HomeScreen(navController)
//        }
////        composable("settings") {
////            SettingsScreen(
////                onHome = { navController.popBackStack() },
////                onProfile = { navController.navigate("profile") }
////            )
////        }
//        composable("detail/{placeId}", arguments = listOf(navArgument("placeId") { type = NavType.StringType })) {
//            backStackEntry ->
//            PlaceDetail( onHome = { navController.popBackStack() }, placeId = backStackEntry.arguments?.getString("placeId") ?: "")
////            mealId = backStackEntry.arguments?.getString("mealId")
//        }
//    }
//}