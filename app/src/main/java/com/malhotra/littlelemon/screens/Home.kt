package com.malhotra.littlelemon.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.malhotra.littlelemon.R
import com.malhotra.littlelemon.navigation.Profile

@Composable
fun Home(navController : NavHostController) {
    Column() {
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painter = painterResource(id = R.drawable.logo),
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

    }
}
@Preview(showBackground = true)
@Composable
fun HomePreview(){
    Home(navController = rememberNavController())
}