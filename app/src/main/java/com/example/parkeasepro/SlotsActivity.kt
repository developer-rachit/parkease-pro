package com.example.parkeasepro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
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

        setContent {
            ParkEaseProTheme {
                SlotsScreen(totalSlot, occupied, availableSlot, onBookSlotClicked = {request ->
                    GlobalScope.launch(Dispatchers.IO) { 
                        val response = ParkingService.create().bookSlot(request.name, request.plateNumber, request.location)
                        if(response.isSuccessful) {
                        }
                    }
                    
                })
            }
        }
    }
}