package com.analistajunior.jogodobebe;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements Runnable {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Handler handler = new Handler();
        handler.postDelayed(this, 3000);
    }


    @Override
    public void run() {
        startActivity(new Intent(this, JogoActivity.class));
        finish();
    }
}
