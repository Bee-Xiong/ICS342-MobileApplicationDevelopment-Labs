package com.ics342.labs

import com.squareup.moshi.Json

data class DataInfo(
    @Json(name = "id") val id: Integer,
    @Json(name = "give_name") val giveName: String,
    @Json(name = "family_name") val familyName: String,
    @Json(name = "age") val age: Integer,
)
