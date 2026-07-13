package com.mobile.data.dto

import kotlinx.serialization.SerialName

data class UsersData(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("company") val company: String,
    @SerialName("username") val username: String,
    @SerialName("email") val email: String,
    @SerialName("address") val address: String,
    @SerialName("zip") val zip: String,
    @SerialName("state") val state: String,
    @SerialName("country") val country: String,
    @SerialName("phone") val phone: String,
    @SerialName("photo") val photo: String
)


