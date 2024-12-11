package com.malhotra.littlelemon.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.malhotra.littlelemon.network.MenuNetworkLocal
import com.malhotra.littlelemon.screens.Home
import com.malhotra.littlelemon.screens.Onboarding
import com.malhotra.littlelemon.screens.Profile

@Composable
fun Navigation(navController : NavHostController, items : List<MenuNetworkLocal>) {
    val sharedPreferences = LocalContext.current.getSharedPreferences("User", Context.MODE_PRIVATE)
    val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)
    
    NavHost(navController = navController,
        startDestination =
    if (isLoggedIn){
        Home.route
    }else{
        Onboarding.route
    }) {
        composable(Home.route){
            Home(navController, items)
        }

        composable(Onboarding.route){
            Onboarding(navController)
        }

        composable(Profile.route){
            Profile(navController)
        }
    }
}