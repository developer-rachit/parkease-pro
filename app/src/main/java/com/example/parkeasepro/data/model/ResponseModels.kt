package com.example.parkeasepro.data.model


data class SlotSearchResponse(
    val totalSlot: Int, val occupied: Int, val available_slot: Int
)

data class BookSlotResponse(
    val msg: String, val status: String
)