package com.example.grinapp

import com.google.gson.annotations.SerializedName

data class Product (
    @SerializedName("id")
    var id: Int ,
    @SerializedName("product")
    var nom: String,
    @SerializedName("contenidor")
    var contenidor: String
)