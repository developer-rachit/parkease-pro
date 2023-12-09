package com.example.parkeasepro.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.parkeasepro.data.model.BookSlotRequest
import com.example.parkeasepro.ui.theme.ParkEaseProTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onSearchClick: (BookSlotRequest) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var plateNumber by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }

    var snackbarVisible by remember { mutableStateOf(false) }

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
                if (location.equals("Delhi", ignoreCase = true) || location.equals(
                        "UK",
                        ignoreCase = true
                    )
                ) {
                    onSearchClick(BookSlotRequest(name, plateNumber, location))
                } else {
                    snackbarVisible = true
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Icon(imageVector = Icons.Default.Search, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Search")
        }

        if (snackbarVisible) {
            Snackbar(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp),
                action = {
                    TextButton(onClick = { snackbarVisible = false }) {
                        Text("Dismiss", color = Color.White)
                    }
                },
                content = {
                    Text("We are currently not available in the provided location.")
                }
            )
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