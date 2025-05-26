package com.ae22mp.parcial2pdm.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ae22mp.parcial2pdm.ui.theme.PurpleC


@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    currentRoute: String,
    navController: NavController,
) {
    val outlinedIcons = listOf(
        Icons.Outlined.Home,
        Icons.Outlined.ShoppingCart,
    )

    val filledIcons = listOf(
        Icons.Filled.Home,
        Icons.Filled.ShoppingCart,
    )

    val routes = listOf("main", "cart")
    val labels = listOf("Home", "Cart")

    NavigationBar(
        modifier = modifier,
        containerColor = Color.White.copy(alpha = 0.85f)
    ) {
        routes.forEachIndexed { index, route ->
            val isSelected = currentRoute == route

            NavigationBarItem(
                selected = isSelected,
                onClick = { navController.navigate(route) },
                icon = {
                    Icon(
                        imageVector = if (isSelected) filledIcons[index] else outlinedIcons[index],
                        contentDescription = labels[index],
                        modifier = Modifier.size(35.dp),
                        tint = if (isSelected) PurpleC else Color.Gray
                    )
                },
                label = {
                    Text(
                        text = labels[index],
                        fontSize = 12.sp,
                        color = if (isSelected) PurpleC else Color.Gray
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}
