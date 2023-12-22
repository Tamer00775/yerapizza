package com.example.yerapizza

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.yerapizza.retrofit.PizzaApi
import com.example.yerapizza.retrofit.RetrofitClient
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pizzaApi = RetrofitClient.retrofit().create(PizzaApi::class.java)

        GlobalScope.launch {
            val result = pizzaApi.findAll();
            if (result != null) {
                Log.d("result : ", result.toString())
            }
        }
    }
}