package com.ae22mp.parcial2pdm.view


import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ae22mp.parcial2pdm.ui.components.BottomBar
import com.ae22mp.parcial2pdm.ui.components.TopBar
import com.ae22mp.parcial2pdm.ui.theme.PurpleC
import com.ae22mp.parcial2pdm.viewmodel.ProductViewModel
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScreen(
    navController: NavController,
    productViewModel: ProductViewModel,
    productId: String
) {
    val product by productViewModel.product.observeAsState()
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        productViewModel.getProductById(productId)
    }
    Scaffold(
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
                    Text(
                        text = product?.name ?: "Name",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 25.sp,
                            color = MaterialTheme.colorScheme.primary
                        ),
                        modifier = Modifier.padding(top = 16.dp)
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        GlideImage(
                            imageModel = { product?.imageUrl },
                            modifier = Modifier
                                .widthIn(max=200.dp)
                                .heightIn(max=300.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .shadow(8.dp, RoundedCornerShape(16.dp))
                                .align(Alignment.Center),
                            imageOptions = ImageOptions(
                                contentScale = ContentScale.Crop
                            ),
                            loading = {
                                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                            },
                            failure = {
                                CircularProgressIndicator()
                            }
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Product Details",
                            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color(0xFF572365)),
                            modifier = Modifier.padding(bottom = 12.dp)
                        )
                        Divider(color = Color.LightGray, thickness = 1.dp)
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Name: ",
                                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color(0xFF572365)),
                            )
                            Text(
                                text = product?.name ?: "Not available",
                                style = TextStyle(fontSize = 16.sp, color = Color.Black)
                            )
                        }
                        Divider(color = Color.LightGray, thickness = 1.dp)
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Category: ",
                                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color(0xFF572365)),
                            )
                            Text(
                                text = product?.category ?: "Not available",
                                style = TextStyle(fontSize = 16.sp, color = Color.Black)
                            )
                        }
                        Divider(color = Color.LightGray, thickness = 1.dp)
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Price: ",
                                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color(0xFF572365)),
                            )
                            Text(
                                text = "${product?.price ?: "Not available"}",
                                style = TextStyle(fontSize = 16.sp, color = Color.Black)
                            )
                        }
                        Divider(color = Color.LightGray, thickness = 1.dp)
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = buildAnnotatedString {
                                    append("Description: ")
                                    withStyle(SpanStyle(fontSize = 16.sp, color = Color.Black, fontWeight = FontWeight.Normal)) {
                                        append(product?.description?: "Not available")
                                    }
                                },
                                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color(0xFF572365), textAlign = TextAlign.Justify)
                            )
                        }
                        Divider(color = Color.LightGray, thickness = 1.dp)
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if(product?.addedToCart == false) {
                            Button(
                                onClick = {
                                    productViewModel.addProductToCart(product!!)
                                    Toast.makeText(context, "Product added to cart", Toast.LENGTH_SHORT).show()
                                },
                                modifier = Modifier
                                    .height(48.dp)
                                    .weight(1f),
                                shape = RoundedCornerShape(12.dp),
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Add,
                                    contentDescription = "Add to cart",
                                    tint = Color.White,
                                    modifier = Modifier.padding(end = 8.dp)
                                )
                                Text(
                                    text = "Add to cart",
                                    color = Color.White,
                                    style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp)
                                )
                            }
                        }
                    }
                }
            }
        }
    )
}