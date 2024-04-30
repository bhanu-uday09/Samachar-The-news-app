package com.msr_mc24.mc_newsapp

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.msr_mc24.mc_newsapp.data.ApiInterface


data class ListItem(
    val id: String,
    val height: Dp,
    val color: Color,
    val text: String,
    val imageResId: Int
)
@Composable
fun Categories(apiInterface: ApiInterface) {

    val items = remember {
        mutableListOf(
            ListItem(
                id = "general",
                210.dp,
                Color(0xFFB5C18E).copy(alpha = 1f),
                "HEADLINES",
                R.drawable.headlines
            ),
            ListItem(
                id = "general",
                180.dp,
                Color(0xFFF7DCB9).copy(alpha = 1f),
                "GENERAL",
                R.drawable.general
            ),
            ListItem(
                id = "business",
                210.dp,
                Color(0xFFACE2E1).copy(alpha = 1f),
                "BUSINESS",
                R.drawable.business
            ),
            ListItem(
                id = "sports",
                180.dp,
                Color(0xFF008DDA).copy(alpha = 1f),
                "SPORTS",
                R.drawable.sport
            ),
            ListItem(
                id = "health",
                210.dp,
                Color(0xFFEFB08C).copy(alpha = 1f),
                "HEALTH",
                R.drawable.health
            ),
            ListItem(
                id = "science",
                180.dp,
                Color(0xFFFA9905).copy(alpha = 1f),
                "SCIENCE",
                R.drawable.science
            ),
            ListItem(
                id = "entertainment",
                210.dp,
                Color(0xFFD74B76).copy(alpha = 1f),
                "ENTERTAINMENT",
                R.drawable.entertainment
            ),
            ListItem(
                id = "technology",
                180.dp,
                Color(0xFFFB6D48).copy(alpha = 1f),
                "TECHNOLOGY",
                R.drawable.tech
            ),
//                ListItem(
//                    Random.nextInt(100, 300).dp,
//                    Color(Random.nextLong(0xFFFFFFFF)).copy(alpha = 1f),
//                    "Text 9",
//                    R.drawable.image9
//                ),
//                ListItem(
//                    Random.nextInt(100, 300).dp,
//                    Color(Random.nextLong(0xFFFFFFFF)).copy(alpha = 1f),
//                    "Text 10",
//                    R.drawable.image10
//                )
        )
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(20.dp, 20.dp, 20.dp, 10.dp)
            .background(color = Color(0xFFFFCBCB), shape = RoundedCornerShape(16.dp))
        ) {
            Image(
                painter = painterResource(id = R.drawable.categories),
                contentDescription = null,
                modifier = Modifier
                    .height(65.dp)
                    .padding(30.dp,15.dp,3.dp,10.dp)
            )
            Text(
                text = "CATEGORIES",
                fontSize = 40.sp,
                color = Color.Black,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.Monospace,
                modifier = Modifier
                    .padding(4.dp,12.dp,23.dp,10.dp)
            )
        }

        var selectedCategory by remember { mutableStateOf("") }
        val context = LocalContext.current

        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp,0.dp,10.dp),
            contentPadding = PaddingValues(4.dp),
            horizontalArrangement = Arrangement.spacedBy(9.dp),
            verticalItemSpacing = 9.dp
        ) {
            items(items) { item ->
                GridBox(
                    item = item,
                    onItemClick = {
                        clickedCategory -> selectedCategory = clickedCategory
                        // Pass intent to open a new activity when the box is clicked
                        val intent = Intent(context, NewsDisplay::class.java)
                        context.startActivity(intent)
                    }
                )
            }
        }
        // Invoke NewsScreen when a category is selected
        if (selectedCategory.isNotBlank()) {
            NewsScreen(apiInterface = apiInterface, category = selectedCategory)
        }
    }
}

@Composable
fun GridBox(item: ListItem, onItemClick: (String) -> Unit) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(item.height)
            .clickable { onItemClick(item.id) },
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(item.height)
                .background(item.color, shape = RoundedCornerShape(8.dp))
                .padding(6.dp),
            contentAlignment = Alignment.Center
        ) {
            Column {
                Image(
                    painter = painterResource(id = item.imageResId),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = item.text,
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}