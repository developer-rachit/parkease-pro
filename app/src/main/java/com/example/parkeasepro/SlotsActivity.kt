package com.example.parkeasepro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.parkeasepro.ui.theme.ParkEaseProTheme

class SlotsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val totalSlot = intent.getIntExtra("totalSlot", 0)
        val occupied = intent.getIntExtra("occupied", 0)
        val availableSlot = intent.getIntExtra("available_slot", 0)

        setContent {
            ParkEaseProTheme {
                SlotsScreen(totalSlot, occupied, availableSlot)
            }
        }
    }
}

@Composable
fun SlotsScreen(totalSlot: Int, occupied: Int, availableSlot: Int) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SlotField(label = "Total Slot", value = totalSlot)

        SlotField(label = "Occupied", value = occupied)

        SlotField(label = "Available Slot", value = availableSlot)

        BookSlotButton()
    }
}

@Composable
fun SlotField(label: String, value: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
    ) {
        Text(label)
        Text(value.toString())
    }
}

@Composable
fun BookSlotButton() {
    val context = LocalContext.current
    Button(
        onClick = {
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    ) {
        Icon(imageVector = Icons.Default.Face, contentDescription = null)
        Spacer(modifier = Modifier.width(8.dp))
        Text("Book a slot")
    }
}

@Preview(showBackground = true)
@Composable
fun SlotsScreenPreview() {
    ParkEaseProTheme {
        SlotsScreen(totalSlot = 10, occupied = 5, availableSlot = 5)
    }
}