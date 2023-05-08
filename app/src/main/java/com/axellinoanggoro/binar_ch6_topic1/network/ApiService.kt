package com.axellinoanggoro.binar_ch6_topic1.network

import com.axellinoanggoro.binar_ch6_topic1.model.GetUserResponseItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("user")
    fun getAllUser(): Call<List<GetUserResponseItem>>
}