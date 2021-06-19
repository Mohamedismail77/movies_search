package com.application.moviestest.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Data<T> (

    @SerializedName("results")
    @Expose
    var data: T? = null


)