package com.ae22mp.parcial2pdm.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ae22mp.parcial2pdm.ui.components.BottomBar
import com.ae22mp.parcial2pdm.ui.components.ProductSearchCard
import com.ae22mp.parcial2pdm.ui.components.TopBar
import com.ae22mp.parcial2pdm.viewmodel.ProductViewModel

@Composable
fun MainScreen(
    navController: NavController,
    productViewModel: ProductViewModel
) {
    val products by productViewModel.products.observeAsState()
    val filteredProducts by productViewModel.filteredProducts.observeAsState()
    LaunchedEffect(Unit) {
        productViewModel.loadProducts()
    }
    Scaffold(
        topBar = {
            TopBar(
                navController = navController,
                title = "Search products"
            )
        },
        bottomBar = {
            BottomBar(
                navController = navController,
                currentRoute = navController.currentBackStackEntry?.destination?.route ?: "main",
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
                    var productQuery by remember { mutableStateOf("") }
                    OutlinedTextField(
                        value = productQuery,
                        onValueChange = {
                            productQuery = it
                            productViewModel.searchProducts(it)
                        },
                        label = { Text("Search a product...") },
                        leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                    )
                    if(productQuery.isNotEmpty()) {
                        LazyColumn {
                            items(filteredProducts?.size ?: 0) {
                                ProductSearchCard(filteredProducts?.get(it), navController)
                            }
                        }
                    } else {
                        LazyColumn {
                            items(products?.size ?: 0) {
                                ProductSearchCard(products?.get(it), navController)
                            }
                        }
                    }
                }
            }
        }
    )
}