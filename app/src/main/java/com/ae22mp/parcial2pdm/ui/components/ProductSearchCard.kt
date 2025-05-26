package com.ae22mp.parcial2pdm.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.R
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ae22mp.parcial2pdm.model.Product
import com.ae22mp.parcial2pdm.ui.theme.BlackC
import com.ae22mp.parcial2pdm.ui.theme.BrownC
import com.ae22mp.parcial2pdm.ui.theme.PurpleC
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ProductSearchCard(
    product: Product?,
    navController: NavController
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { navController.navigate("product/${product?.id}") },
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
        ) {
            GlideImage(
                imageModel = { product?.imageUrl },
                modifier = Modifier
                    .width(90.dp)
                    .height(130.dp)
                    .padding(end = 12.dp),
                imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                loading = {
                    Box(
                        modifier = Modifier
                            .width(90.dp)
                            .height(130.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = PurpleC, strokeWidth = 2.dp)
                    }
                },
                failure = {
                    CircularProgressIndicator()
                }
            )
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = product?.name.toString(),
                    style = MaterialTheme.typography.titleMedium,
                    color = BlackC,
                    maxLines = 2
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = product?.description.toString(),
                    style = MaterialTheme.typography.bodySmall,
                    color = BrownC,
                    maxLines = 3
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Category: ${product?.category}",
                    style = MaterialTheme.typography.bodySmall,
                    color = BrownC,
                    )

            }
        }
    }
}