package com.example.parkeasepro

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.parkeasepro.data.model.BookSlotRequest
import com.example.parkeasepro.presentation.SlotsScreen
import com.example.parkeasepro.ui.theme.ParkEaseProTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SlotsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val totalSlot = intent.getIntExtra("totalSlot", 0)
        val occupied = intent.getIntExtra("occupied", 0)
        val availableSlot = intent.getIntExtra("available_slot", 0)

        val username = intent.getStringExtra("name")
        val plate = intent.getStringExtra("plate")
        val place = intent.getStringExtra("place")

        setContent {
            ParkEaseProTheme {
                SlotsScreen(totalSlot, occupied, availableSlot, onBookSlotClicked = { request ->
                    GlobalScope.launch(Dispatchers.IO) {

                        var bookSlotRequest = BookSlotRequest(username!!, plate!!, place!!)
                        val response = ParkingService.create().bookSlot(bookSlotRequest)
//                        val response = ParkingService.create().bookSlot(request.name, request.plateNumber, request.location)

                        if (response.isSuccessful) {
                            if (response.code() == 200) {

                                runOnUiThread {
                                    Toast.makeText(
                                        applicationContext,  response.body()!!.msg, Toast.LENGTH_LONG
                                    ).show()

                                }

                            }
                            runOnUiThread {
                                Toast.makeText(
                                    applicationContext,
                                    response.body()!!.msg,
                                    Toast.LENGTH_LONG
                                ).show()
                            }

                        } else {
                            runOnUiThread {
                                Toast.makeText(
                                    applicationContext,
                                    response.body()!!.msg,
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    }

                })
            }
        }
    }
}