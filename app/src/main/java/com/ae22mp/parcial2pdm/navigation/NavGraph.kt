package com.ae22mp.parcial2pdm.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ae22mp.parcial2pdm.view.CartScreen
import com.ae22mp.parcial2pdm.view.MainScreen
import com.ae22mp.parcial2pdm.view.ProductScreen
import com.ae22mp.parcial2pdm.viewmodel.ProductViewModel

object Routes {
    const val MAIN = "main"
    const val PRODUCT = "product/{id}"
    const val CART = "cart"
}

@Composable
fun NavGraph(startDestination: String = Routes.MAIN) {
    val navController = rememberNavController()
    val productViewModel: ProductViewModel = viewModel()
    NavHost(navController = navController, startDestination = startDestination) {
        composable(Routes.MAIN) {
            MainScreen(navController, productViewModel)
        }
        composable(Routes.PRODUCT) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("id")
            ProductScreen(navController, productViewModel, productId ?: "")
        }
        composable(Routes.CART) {
            CartScreen(navController, productViewModel)
        }

    }
}