package com.example.yerapizza.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yerapizza.R;
import com.example.yerapizza.adapter.SimilarAdapter;
import com.example.yerapizza.data.Pizza;
import com.example.yerapizza.retrofit.PizzaApi;
import com.example.yerapizza.retrofit.RetrofitClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Response;

public class DetailActivity1 extends AppCompatActivity {
    private Pizza object;
    private ImageView backBtn, itemImg;
    private TextView priceKgTxt, titleTxt, descriptionTxt, ratingTxt;
    private RatingBar ratingBar;
    private TextView weightTxt, plusBtn, minusBtn, totalTxt;
    private int weight = 1;
    private RecyclerView.Adapter similarAdapter;
    private RecyclerView recyclerViewSimilar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        getBandles();
        initView();
        setVariable();
        initSimilarList();
    }

    private void initSimilarList() {
        recyclerViewSimilar = findViewById(R.id.similarView);
        recyclerViewSimilar.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        new GetDataAsyncTask().execute();
    }

    private class GetDataAsyncTask extends AsyncTask<Void, Void, List<Pizza>> {
        @Override
        protected List<Pizza> doInBackground(Void... voids) {
            try {
                return getData();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        protected void onPostExecute(List<Pizza> data) {
            similarAdapter = new SimilarAdapter(data);
            recyclerViewSimilar.setAdapter(similarAdapter);
        }
    }

    private List<Pizza> getData() throws IOException {
        PizzaApi pizzaApi = RetrofitClient.INSTANCE.retrofit().create(PizzaApi.class);
        Response<List<Pizza>> response = pizzaApi.findAll().execute();
        if (response.isSuccessful()) {
            return response.body();
        } else {
            // Handle error
            return Collections.emptyList();
        }
    }

    private void setVariable() {
        backBtn.setOnClickListener(v -> finish());

        String imagePath = object.getImagePath();

        Glide.with(DetailActivity1.this)
                .load(imagePath)
                .into(itemImg);

        priceKgTxt.setText(object.getPrice()+"$/Kg");
        titleTxt.setText(object.getDescription());
        descriptionTxt.setText(object.getDescription());
        ratingTxt.setText("(" + 5+")");
        ratingBar.setRating((float) 5);
        totalTxt.setText((weight*object.getPrice())+"$");

        plusBtn.setOnClickListener(v -> {
            weight=weight+1;
            weightTxt.setText(weight+" kg");
            totalTxt.setText((weight*object.getPrice())+"$");

        });
        minusBtn.setOnClickListener(v -> {
            if(weight>1){
                weight=weight-1;
                weightTxt.setText(weight+" kg");
                totalTxt.setText((weight*object.getPrice())+"$");

            }
        });
    }

    private void initView() {
        backBtn=findViewById(R.id.backBtn);
        itemImg=findViewById(R.id.img);
        priceKgTxt=findViewById(R.id.priceKgTxt);
        titleTxt=findViewById(R.id.titleTxt);
        descriptionTxt=findViewById(R.id.descriptionTxt);
        ratingBar=findViewById(R.id.ratingBar);
        ratingTxt=findViewById(R.id.ratingTxt);
        weightTxt=findViewById(R.id.weightTxt);
        plusBtn=findViewById(R.id.plusBtn);
        minusBtn=findViewById(R.id.minusBtn);
        totalTxt=findViewById(R.id.totalTxt);
    }

    private void getBandles() {
        object= (Pizza) getIntent().getSerializableExtra("object");
    }
}