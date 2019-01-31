package com.mosso.convertmoney.models.response

import com.google.gson.annotations.SerializedName

data class Currency(@SerializedName("base") private val base: String,
                    @SerializedName("date") private val date: String,
                    @SerializedName("rates") private val rates: String
)

