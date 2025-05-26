package com.ae22mp.parcial2pdm.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ae22mp.parcial2pdm.ui.components.BottomBar
import com.ae22mp.parcial2pdm.ui.components.ProductSearchCard
import com.ae22mp.parcial2pdm.ui.components.TopBar
import com.ae22mp.parcial2pdm.viewmodel.ProductViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    navController: NavController,
    productViewModel: ProductViewModel,
) {
    val products by productViewModel.products.observeAsState()
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        productViewModel.loadProducts()
    }
    Scaffold(
        topBar = {
            TopBar(
                navController = navController,
                title = "Cart"
            )
        },
        bottomBar = {
            BottomBar(
                navController = navController,
                currentRoute = navController.currentBackStackEntry?.destination?.route ?: "cart",
            )
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 10.dp)
                    .padding(paddingValues)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LazyColumn {
                        items(products?.size ?: 0) {
                            val product = products?.get(it)
                            if(product?.addedToCart == true) {
                                ProductSearchCard(products?.get(it), navController)
                            }
                        }
                    }
                }
            }
        }
    )
}