package com.example.yerapizza.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import com.example.yerapizza.R;

public class FirstPageActivity extends AppCompatActivity {
    private Button introButton;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.first_page);

        introButton = findViewById(R.id.introBtn);
        introButton.setOnClickListener(event -> startActivity(new Intent(
                FirstPageActivity.this, MainActivity.class)));
    }
}
