package com.valor_paytech.task.repository.model

import com.google.gson.annotations.SerializedName


data class MainModel(
    @SerializedName("products" ) var products : ArrayList<Products> = arrayListOf()
)