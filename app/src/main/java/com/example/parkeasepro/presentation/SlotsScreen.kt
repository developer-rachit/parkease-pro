package com.example.parkeasepro.presentation

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
import com.example.parkeasepro.data.model.BookSlotRequest
import com.example.parkeasepro.data.model.SlotSearchRequest
import com.example.parkeasepro.ui.theme.ParkEaseProTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.Dispatcher


@Composable
fun SlotsScreen(totalSlot: Int, occupied: Int, availableSlot: Int, onBookSlotClicked: (BookSlotRequest) -> Unit) {
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

        BookSlotButton("Rachit", "XYZAA", "UK", onBookSlotClicked)
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
fun BookSlotButton(name: String, plateNumber: String, location: String, onBookSlotClicked: (BookSlotRequest) -> Unit) {
    Button(
        onClick = {
                  onBookSlotClicked(BookSlotRequest(name, plateNumber, location))
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

fun bookSlot(name: String, plateNumber: String, location: String) {
    GlobalScope.launch {

    }
}

@Preview(showBackground = true)
@Composable
fun SlotsScreenPreview() {
    ParkEaseProTheme {
        SlotsScreen(totalSlot = 10, occupied = 5, availableSlot = 5, onBookSlotClicked = {})
    }
}