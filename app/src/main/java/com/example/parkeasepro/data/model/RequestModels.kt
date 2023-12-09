package com.example.parkeasepro.data.model


data class SlotSearchRequest(
    val location: String
)

data class BookSlotRequest(
    val username: String,
    val number_plate: String,
    val location: String
)

