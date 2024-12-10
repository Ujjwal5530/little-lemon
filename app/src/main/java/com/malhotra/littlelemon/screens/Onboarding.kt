package com.malhotra.littlelemon.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.malhotra.littlelemon.R
import com.malhotra.littlelemon.navigation.Home
import com.malhotra.littlelemon.ui.theme.Primary1
import com.malhotra.littlelemon.ui.theme.Primary2
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun Onboarding(navHostController: NavHostController){

    var firstName by remember {
        mutableStateOf("")
    }

    var lastName by remember {
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current
    val coroutine = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        Image(modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .padding(10.dp)
            .height(40.dp)
            .fillMaxWidth(),
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo" )

        Box(modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(Primary1)){
            Text(text = "Let's get to know you" ,
                color = Color.White,
                fontSize = 32.sp,
                fontFamily = FontFamily(Font(R.font.markazi_text)) ,
                modifier = Modifier.align(Alignment.Center))
        }

        Text(text = "Personal Information",
            color = Color.Black,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily(Font(R.font.markazi_text)),
            modifier = Modifier.padding(vertical = 32.dp, horizontal = 16.dp)
        )

        Column(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(value = firstName,
                onValueChange = {firstName = it},
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                label = { Text(text = "First Name")})

            OutlinedTextField(value = lastName,
                onValueChange = {lastName = it},
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                label = { Text(text = "Last Name")})

            OutlinedTextField(value = email,
                onValueChange = {email = it},
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                label = { Text(text = "Email", fontFamily = FontFamily(Font(R.font.karla2)))})

            Button(onClick = {
                             if (firstName.isBlank() && lastName.isBlank() && email.isBlank()){
                                 Toast.makeText(context,
                                     "Registration unsuccessful. Please enter all data." ,
                                     Toast.LENGTH_SHORT).show()
                             } else {
                                 coroutine.launch {
                                     val sharedPreferences = context.getSharedPreferences("User",
                                         Context.MODE_PRIVATE)
                                     sharedPreferences.edit()
                                         .putBoolean("isLoggedIn", true)
                                         .putString("firstName", firstName)
                                         .putString("lastName", lastName)
                                         .putString("email", email)
                                         .apply()

                                     withContext(Dispatchers.Main){
                                         Toast.makeText(context,
                                             "Registration successful!" ,
                                             Toast.LENGTH_SHORT).show()
                                         navHostController.popBackStack()
                                         navHostController.navigate(Home.route)
                                     }
                                 }
                             }
            },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 56.dp),
                colors = ButtonDefaults.buttonColors(Primary2)) {
                Text(text = "Register",
                    color = Color.Black,
                    fontSize = 24.sp,
                    fontFamily = FontFamily(Font(R.font.markazi_text))
                    )
            }

        }

    }
}
@Preview (showBackground = true)
@Composable
fun OnboardingPreview(){
    Onboarding(navHostController = rememberNavController())
}