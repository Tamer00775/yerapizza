package com.example.yerapizza.retrofit

import com.example.yerapizza.data.Pizza
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.Path

interface PizzaApi {
    @GET("/api")
    fun findAll() : Call<List<Pizza>>

    @GET("/api/pizza/{id}")
    fun findById(@Path("id") id: Long) : Call<Pizza>

    @DELETE("/api/pizza/{id}")
    fun deleteById(@Path("id") id : Long) : Call<Void>
}