package com.example.parkeasepro.data.model

data class BookSlotResponse(
    val response: String
)

data class SlotSearchResponse(
    val total: Int,
    val occupied: Int,
    val available: Int
)