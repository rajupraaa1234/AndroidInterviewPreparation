package com.mobile.data.network

import com.mobile.data.dto.UsersData
import retrofit2.Response
import retrofit2.http.GET

interface InterviewService {
    @GET("/users")
    suspend fun getUsers(): Response<List<UsersData>>
}