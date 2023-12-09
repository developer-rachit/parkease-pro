package com.example.parkeasepro.data.services

import com.example.parkeasepro.data.model.BookSlotRequest
import com.example.parkeasepro.data.model.BookSlotResponse
import com.example.parkeasepro.data.model.SlotSearchResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ParkingServiceApi {
    @GET("/api/search/{location}")
    suspend fun getSlots(
        @Path("location") location: String
    ): Response<SlotSearchResponse>

    @POST("/api/book-slot")
    suspend fun bookSlot(
        @Body search: BookSlotRequest
    ): Response<BookSlotResponse>
}