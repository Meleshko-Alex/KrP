package com.example.krp.domain.models

data class NewRecord(
    val carNumber: String,
    val driverFullName: String,
    val date: String,
    val time: String,
    val creator: String,
    var numberOfPassengers: String = "0",
    var passengersFullName: String = "",
    var additionalInfo: String = ""
)