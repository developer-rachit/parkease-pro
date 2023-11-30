package com.example.parkeasepro

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.parkeasepro.ui.theme.ParkEaseProTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ParkEaseProTheme {
                HomeScreen(onSearchClick = {request ->

                    GlobalScope.launch(Dispatchers.IO) {
                        val response = ParkingService.create().getSlots(request.name, request.plateNumber, request.location)
                        if(response.isSuccessful) {
                            val slotsResponse = response.body()

                            startActivity(Intent(this@MainActivity, SlotsActivity::class.java).apply {
                                putExtra("totalSlot", slotsResponse?.total ?: 0)
                                putExtra("occupied", slotsResponse?.occupied ?: 0)
                                putExtra("available_slot", slotsResponse?.available ?: 0)
                            })
                        } else {

                        }
                    }
                })
            }
        }
    }
}

data class SearchRequest(
    val name: String,
    val plateNumber: String,
    val location: String
)

data class SearchResponse(
    val total: Int,
    val occupied: Int,
    val available: Int
)

object ParkingService {
    private const val BASE_URL = "base_url"

    fun create(): ParkingServiceApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ParkingServiceApi::class.java)
    }
}

interface ParkingServiceApi {
    @GET("/api/search")
    suspend fun getSlots(
        @Query("name") name: String,
        @Query("plate_number") plateNumber: String,
        @Query("location") location: String
    ): Response<SearchResponse>
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onSearchClick: (SearchRequest) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var plateNumber by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = plateNumber,
            onValueChange = { plateNumber = it },
            label = { Text("Plate Number") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = location,
            onValueChange = { location = it },
            label = { Text("Location") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        Button(
            onClick = {
                // Trigger the search action
                onSearchClick(SearchRequest(name, plateNumber, location))
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Icon(imageVector = Icons.Default.Search, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Search")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ParkEaseProTheme {
        HomeScreen(onSearchClick = {})
    }
}