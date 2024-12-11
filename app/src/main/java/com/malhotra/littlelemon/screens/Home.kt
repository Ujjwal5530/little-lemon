package com.malhotra.littlelemon.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.malhotra.littlelemon.R
import com.malhotra.littlelemon.navigation.Profile
import com.malhotra.littlelemon.network.MenuNetworkLocal
import com.malhotra.littlelemon.ui.theme.Primary1
import com.malhotra.littlelemon.ui.theme.Primary2

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun Home(navController : NavHostController, items : List<MenuNetworkLocal>) {


    Column() {
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Little Lemon Logo",
                modifier = Modifier
                    .height(80.dp)
                    .padding(top = 16.dp, bottom = 16.dp, start = 50.dp)
                    .weight(1F, true),
            )

            Image(painter = painterResource(id = R.drawable.profile),
                contentDescription = "Profile",
                modifier = Modifier
                    .size(80.dp)
                    .padding(horizontal = 16.dp, vertical = 16.dp)
                    .clickable { navController.navigate(Profile.route) })
        }

        var searchPhrase by remember {
            mutableStateOf("")
        }
        Box(modifier = Modifier
            .fillMaxWidth()
            .background(Primary1)){

            Text(text = "Little Lemon",
                modifier = Modifier.padding(start = 16.dp, top = 4.dp),
                fontSize = 60.sp,
                fontFamily = FontFamily(Font(R.font.markazi_text)),
                color = Primary2
            )

            Text(text = "Chicago",
                modifier = Modifier.padding(start = 16.dp, top = 56.dp),
                fontSize = 36.sp,
                fontFamily = FontFamily(Font(R.font.markazi_text)),
                color = Color.White
            )

            Row(
                modifier = Modifier.padding(
                    start = 16.dp,
                    top = 96.dp,
                    end = 16.dp,
                    bottom = 16.dp
                )
            ) {
                Text(text = stringResource(id = R.string.description),
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.karla2)),
                    color = Color.White,
                    modifier = Modifier
                        .padding(end = 16.dp, top = 25.dp)
                        .weight(1F)
                    )

                Image(painter = painterResource(id = R.drawable.hero_image),
                    contentDescription = "Hero section",
                    modifier = Modifier
                        .width(100.dp)
                        .height(120.dp)
                        .clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop)
            }


            TextField(value = searchPhrase, 
                onValueChange = { searchPhrase = it },
                placeholder = { Text(text = "Search Food Item",
                    color = Primary1,
                    fontFamily = FontFamily(Font(R.font.karla2))
                )},
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 240.dp, bottom = 16.dp)
                    .fillMaxWidth()
                    .clip(shape = MaterialTheme.shapes.small)
                    .background(Color.White),
                leadingIcon = { Icon( imageVector = Icons.Default.Search, contentDescription = "") }
                )

        }

        var selectedCategory by remember {
            mutableStateOf("")
        }

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = { selectedCategory = "All" },
                colors = ButtonDefaults.buttonColors(Color.LightGray)) {
                Text(text = "All",
                    color = Primary1,
                    fontFamily = FontFamily(Font(R.font.karla2)),
                    fontWeight = FontWeight.Bold)
            }

            Button(onClick = { selectedCategory = "Starters" },
                colors = ButtonDefaults.buttonColors(Color.LightGray)) {
                Text(text = "Starters",
                    color = Primary1,
                    fontFamily = FontFamily(Font(R.font.karla2)),
                    fontWeight = FontWeight.Bold)
            }

            Button(onClick = { selectedCategory = "Desserts" },
                colors = ButtonDefaults.buttonColors(Color.LightGray)) {
                Text(text = "Desserts",
                    color = Primary1,
                    fontFamily = FontFamily(Font(R.font.karla2)),
                    fontWeight = FontWeight.Bold)
            }

            Button(onClick = { selectedCategory = "Mains" },
                colors = ButtonDefaults.buttonColors(Color.LightGray)) {
                Text(text = "Mains",
                    color = Primary1,
                    fontFamily = FontFamily(Font(R.font.karla2)),
                    fontWeight = FontWeight.Bold)
            }

        }

        
        val menuItems = if (searchPhrase.isNotEmpty()){
            items.filter { it.title.lowercase().contains(searchPhrase)}
        }else {
            when(selectedCategory){
                "Starters" -> items.filter { it.category.lowercase() == selectedCategory.lowercase() }
                "Desserts" -> items.filter { it.category.lowercase() == selectedCategory.lowercase() }
                "Mains" -> items.filter { it.category.lowercase() == selectedCategory.lowercase() }
                else -> items

            }
        }
        MenuItems(items = menuItems)

    }
}
@Preview(showBackground = true)
@Composable
fun HomePreview(){
//    Home(navController = rememberNavController(), items = listOf(
//        MenuNetworkLocal(1, "Greek Salad", "Greek Salad","12", "https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/greekSalad.jpg?raw=true", "starters"),
//        MenuNetworkLocal(2, "Greek Salad", "Greek Salad","12", "https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/greekSalad.jpg?raw=true", "starters")
//    )
//        )
    
    //CategoryItems(category = "Starters", selectedCategory = "Start")
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuItems(items : List<MenuNetworkLocal>){

    LazyColumn(modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

        items(
            items = items,
            itemContent = {
                Column(modifier = Modifier
                    .border(1.dp, color = Primary1, shape = MaterialTheme.shapes.small)
                    .padding(6.dp)) {
                    Text(
                        text = it.title,
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.karla2)),
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 8.dp, top = 16.dp)
                    )

                    Row {
                        Column(modifier = Modifier.weight(1F)) {
                            Text(text = it.description,
                                fontSize = 12.sp,
                                fontFamily = FontFamily(Font(R.font.karla2)),
                                color = Color.Black,
                                modifier = Modifier.padding(top = 4.dp, bottom = 4.dp, end = 16.dp, start = 8.dp),
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis)

                            Text(text = "$${it.price}",
                                fontSize = 14.sp,
                                fontFamily = FontFamily(Font(R.font.karla2)),
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(start = 8.dp))
                        }


                        GlideImage(model = it.image, contentDescription = it.title,
                            modifier = Modifier
                                .size(70.dp)
                                .padding(end = 16.dp, bottom = 16.dp))
                    }
                }

            })
    }


}

@Composable
fun CategoryItems(category : String){


}

