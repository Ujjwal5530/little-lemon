package com.malhotra.littlelemon.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.malhotra.littlelemon.R
import com.malhotra.littlelemon.navigation.Home
import com.malhotra.littlelemon.navigation.Onboarding
import com.malhotra.littlelemon.ui.theme.Primary1
import com.malhotra.littlelemon.ui.theme.Primary2

@Composable
fun Profile(navController : NavHostController) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("User", Context.MODE_PRIVATE)
    val firstName = sharedPreferences.getString("firstName", "")
    val lastName = sharedPreferences.getString("lastName", "")
    val email = sharedPreferences.getString("email", "")


    Column(modifier = Modifier.fillMaxWidth()) {
        Image(painter = painterResource(id = R.drawable.logo),
            contentDescription = "Little Lemon Logo",
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .padding(10.dp))

        Text(text = "Personal Information",
            modifier = Modifier.padding(start = 16.dp, top = 92.dp, bottom = 32.dp),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily(Font(R.font.markazi_text))
        )

        Column(modifier = Modifier
            .fillMaxWidth()) {
            InfoLabel(label = "First Name")
            if (firstName != null) {
                InfoText(info = firstName)
            }

            InfoLabel(label = "Last Name")
            if (lastName != null) {
                InfoText(info = lastName)
            }

            InfoLabel(label = "Email")
            if (email != null) {
                InfoText(info = email)
            }
        }

        Button(onClick = {
            sharedPreferences.edit().clear().apply()

            navController.popBackStack(Home.route,true)
            navController.navigate(Onboarding.route)
                         },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 56.dp),
            colors = ButtonDefaults.buttonColors(Primary2)) {
            Text(text = "Log out",
                color = Color.Black,
                fontSize = 24.sp,
                fontFamily = FontFamily(Font(R.font.markazi_text))
            )
        }


    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview(){
    Profile(navController = rememberNavController())
}

@Composable
fun InfoLabel(label : String){
    Text(text = label,
        modifier = Modifier.padding(start = 16.dp, bottom = 2.dp),
        fontSize = 12.sp,
        fontFamily = FontFamily(Font(R.font.markazi_text)))
}

@Composable
fun InfoText(info: String){
    Text(text = info,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, bottom = 16.dp, end = 16.dp)
            .border(width = 1.dp, color = Primary1, shape = MaterialTheme.shapes.small)
            .height(40.dp)
            .padding(8.dp, 10.dp),
        fontSize = 16.sp,
        fontFamily = FontFamily(Font(R.font.markazi_text)),
        color = Primary1
    )
}