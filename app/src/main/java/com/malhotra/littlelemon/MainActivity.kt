package com.malhotra.littlelemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.malhotra.littlelemon.navigation.Navigation
import com.malhotra.littlelemon.network.AppDatabase
import com.malhotra.littlelemon.network.MenuNetwork
import com.malhotra.littlelemon.ui.theme.LittleLemonTheme
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class MainActivity : ComponentActivity() {

    private val client = HttpClient(Android){
        install(ContentNegotiation){
            json(json = Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            },contentType = ContentType.Text.Plain)
        }
    }

    private suspend fun fetchData() : MenuNetwork{
        return client.get("https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json")
            .body()

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = AppDatabase.getDatabase(this)

        lifecycleScope.launch(Dispatchers.IO) {
            val items = fetchData().menu.map { it.toLocal() }

            runOnUiThread {
                database.dao().insertItems(items)
            }
        }

        //enableEdgeToEdge()
        setContent {
            LittleLemonTheme {
                val menuItemsLocal by database.dao().getMenuItems().observeAsState(emptyList())

                Surface(modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()
                    Navigation(navController, menuItemsLocal)
                }
            }
        }
    }
}