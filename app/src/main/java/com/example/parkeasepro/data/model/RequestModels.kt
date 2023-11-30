package com.example.parkeasepro.data.model


data class SlotSearchRequest(
    val location: String
)

data class BookSlotRequest(
    val name: String,
    val plateNumber: String,
    val location: String
)