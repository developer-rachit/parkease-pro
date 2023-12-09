package com.example.parkeasepro

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.parkeasepro.data.services.ParkingServiceApi
import com.example.parkeasepro.presentation.HomeScreen
import com.example.parkeasepro.ui.theme.ParkEaseProTheme
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ParkEaseProTheme {
                HomeScreen(onSearchClick = { request ->

                    GlobalScope.launch(Dispatchers.IO) {
                        val response = ParkingService.create().getSlots(request.location)
                        if (response.isSuccessful) {
                            val slotsResponse = response.body()
                            startActivity(
                                Intent(
                                    this@MainActivity,
                                    SlotsActivity::class.java
                                ).apply {
                                    putExtra("totalSlot", slotsResponse?.totalSlot ?: 0)
                                    putExtra("occupied", slotsResponse?.occupied ?: 0)
                                    putExtra("available_slot", slotsResponse?.available_slot ?: 0)
                                    putExtra("name", request?.username ?: "xyz")
                                    putExtra("plate", request?.number_plate ?: "uk1011")
                                    putExtra("place", request?.location ?: "delhi")
                                })
                        } else {

                        }
                    }
                })
            }
        }
    }
}

object ParkingService {
    private const val BASE_URL = "http://192.168.2.71:8080"

    fun create(): ParkingServiceApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ParkingServiceApi::class.java)
    }
}
