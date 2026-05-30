package com.mobile.data.dto

import com.google.gson.annotations.SerializedName

data class UsersData(
    @SerializedName("id") var id: Int,
    @SerializedName("name") var name: String,
    @SerializedName("company") var company: String,
    @SerializedName("username") var username: String,
    @SerializedName("email") var email: String,
    @SerializedName("address") var address: String,
    @SerializedName("zip") var zip: String,
    @SerializedName("state") var state: String,
    @SerializedName("country") var country: String,
    @SerializedName("phone") var phone: String,
    @SerializedName("photo") var photo: String
)

