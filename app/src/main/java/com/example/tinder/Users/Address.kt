package com.example.tinder.Users

data class Address(
    var city: String,
    var geo: Geo,
    var street: String,
    var suite: String,
    var zipcode: String
)