package com.example.ucp2.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ucp2.R

@Composable
fun MainScreen(
    onAddBarangClick: () -> Unit,
    onListBarangClick: () -> Unit,
    onAddSuplierClick: () -> Unit,
    onListSuplierClick: () -> Unit
) {
    HomePage(
        onAddBarangClick = onAddBarangClick,
        onListBarangClick = onListBarangClick,
        onAddSuplierClick = onAddSuplierClick,
        onListSuplierClick = onListSuplierClick
    )
}

@Composable
fun HomePage(
    onAddBarangClick: () -> Unit,
    onListBarangClick: () -> Unit,
    onAddSuplierClick: () -> Unit,
    onListSuplierClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFFFFEB3B), Color(0xFFAB0000))
                )
            )
    ) {
        // Navbar Section
        NavbarCard()

        // Content Section
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(bottom = 33.dp),
            contentAlignment = Alignment.Center
        ) {
            ContentCard(
                onAddBarangClick = onAddBarangClick,
                onListBarangClick = onListBarangClick,
                onAddSuplierClick = onAddSuplierClick,
                onListSuplierClick = onListSuplierClick
            )
        }
    }
}

@Composable
fun NavbarCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF176))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = android.R.drawable.ic_menu_sort_by_size),
                contentDescription = "Menu Icon",
                modifier = Modifier.size(32.dp),
                tint = Color(0xFF1E88E5)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    "Buku Kita",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1E88E5)
                )
                Text("Welcome Back!", fontSize = 14.sp, color = Color.Gray)
            }
            Image(
                painter = painterResource(id = R.drawable.albert),
                contentDescription = "Profile Image",
                modifier = Modifier
                    .size(48.dp)
            )
        }
    }
}

@Composable
fun ContentCard(
    onAddBarangClick: () -> Unit,
    onListBarangClick: () -> Unit,
    onAddSuplierClick: () -> Unit,
    onListSuplierClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Tambahkan gambar di atas card
        Image(
            painter = painterResource(id = R.drawable.main),
            contentDescription = "Header Image",
            modifier = Modifier
                .size(200.dp) //
                .padding(bottom = 16.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            CardItem(
                title = "Add Product",
                iconResId = R.drawable.addproduk,
                onClick = onAddBarangClick
            )
            CardItem(
                title = "List Product",
                iconResId = R.drawable.produk,
                onClick = onListBarangClick
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            CardItem(
                title = "Add Suplier",
                iconResId = R.drawable.addsuplier,
                onClick = onAddSuplierClick
            )
            CardItem(
                title = "List Suplier",
                iconResId = R.drawable.listsuplier,
                onClick = onListSuplierClick
            )
        }
    }
}

@Composable
fun CardItem(title: String, iconResId: Int, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .size(150.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Gambar
            Image(
                painter = painterResource(id = iconResId),
                contentDescription = "$title Icon",
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            // Teks
            Text(
                title,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                color = Color(0xFF1E88E5)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    MainScreen(
        onAddBarangClick = { println("Add Barang clicked") },
        onListBarangClick = { println("List Barang clicked") },
        onAddSuplierClick = { println("Add Suplier clicked") },
        onListSuplierClick = { println("List Suplier clicked") }
    )
}
