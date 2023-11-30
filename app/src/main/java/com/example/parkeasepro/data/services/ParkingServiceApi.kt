package com.example.parkeasepro.data.services

import com.example.parkeasepro.data.model.SlotSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ParkingServiceApi {
    @GET("/api/search")
    suspend fun getSlots(
        @Query("location") location: String
    ): Response<SlotSearchResponse>

    @POST("/api/bookSlot")
    suspend fun bookSlot(
        @Path("name") name: String,
        @Path("plate_number") plateNumber: String,
        @Path("location") location: String
    ): Response<String>
}