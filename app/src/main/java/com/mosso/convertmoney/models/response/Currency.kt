package com.mosso.convertmoney.models.response

import com.google.gson.annotations.SerializedName

data class Currency(@SerializedName("base")  val base: String?,
                    @SerializedName("date")  val date: String?,
                    @SerializedName("rates")  val rates: TypeCurrency?){



}

