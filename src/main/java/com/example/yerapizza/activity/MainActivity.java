package com.example.yerapizza.activity;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yerapizza.R;
import com.example.yerapizza.adapter.BestDealAdapter;
import com.example.yerapizza.data.Pizza;
import com.example.yerapizza.retrofit.PizzaApi;
import com.example.yerapizza.retrofit.RetrofitClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter catAdapter, bestDealAdapter;
    private RecyclerView recyclerViewCat, recyclerViewBestDeal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecyclerviewCat();
        initRecyclerViewBestDeal();

    }

    private void initRecyclerviewCat() {

    }

    private void initRecyclerViewBestDeal() {
        recyclerViewBestDeal = findViewById(R.id.bestView);
        recyclerViewBestDeal.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        new AsyncTask<Void, Void, List<Pizza>>() {
            @Override
            protected List<Pizza> doInBackground(Void... voids) {
                try {
                    return getData();
                } catch (IOException e) {
                    e.printStackTrace();
                    return Collections.emptyList();
                }
            }

            @Override
            protected void onPostExecute(List<Pizza> data) {
                super.onPostExecute(data);
                bestDealAdapter = new BestDealAdapter(data);
                recyclerViewBestDeal.setAdapter(bestDealAdapter);
            }
        }.execute();
    }


    public List<Pizza> getData() throws IOException {
        PizzaApi pizzaApi = RetrofitClient.INSTANCE.retrofit().create(PizzaApi.class);
        Response<List<Pizza>> response = pizzaApi.findAll().execute();
        if (response.isSuccessful()) {
            return response.body();
        } else {
            // Handle error
            return Collections.emptyList();
        }
    }
}