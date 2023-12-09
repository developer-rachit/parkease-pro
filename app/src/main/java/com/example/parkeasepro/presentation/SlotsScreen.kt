package com.example.parkeasepro.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
        verticalArrangement = Arrangement.Center
    ) {
        sLotList(
            totalSlot = totalSlot, occupied = occupied, availableSlot = availableSlot, onSlotSelected = {}
        )

        SlotField(label = "Total Slot", value = totalSlot)

        SlotField(label = "Occupied", value = occupied)

        SlotField(label = "Available Slot", value = availableSlot)

        BookSlotButton("Rachit", "XYZAA", "UK", onBookSlotClicked)
    }
}

@Composable
fun sLotList(totalSlot: Int, occupied: Int, availableSlot: Int, onSlotSelected: () -> Unit) {
    var selectedSlots by remember { mutableStateOf(mutableSetOf<Int>()) }

    LazyVerticalGrid(
            modifier = Modifier.background(MaterialTheme.colorScheme.background),
            columns = GridCells.Fixed(10)) {
            items(totalSlot) { index ->
                val isOccupied = index < occupied
                val isAvailable = index in occupied until occupied + availableSlot
                val isSelected = index in selectedSlots

                val color = when {
                    isOccupied -> Color.Green
                    isAvailable -> if (isSelected) Color.Green else Color.Gray
                    else -> Color.Transparent
                }


                    Box(
                        modifier = Modifier
                            .padding(top = 5.dp, end = 5.dp)
                            .size(30.dp)
                            .background(color)
                            .clickable {
                                if (isAvailable) {
                                    if (isSelected) {
                                        selectedSlots -= index
                                    } else {
                                        selectedSlots += index
                                    }
                                    onSlotSelected()
                                }
                            }
                    )

            }
        }
}

@Composable
fun SlotField(label: String, value: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),

    ) {
        Text(label)
        Spacer(modifier = Modifier.width(30.dp))
        Text(value.toString())
    }
}

@Composable
fun BookSlotButton(name: String, plateNumber: String, place: String, onBookSlotClicked: (BookSlotRequest) -> Unit) {
    Button(
        onClick = {
                  onBookSlotClicked(BookSlotRequest(name, plateNumber, place))
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(top = 16.dp)
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
        SlotsScreen(totalSlot = 40, occupied = 27, availableSlot = 13, onBookSlotClicked = {})
    }
}