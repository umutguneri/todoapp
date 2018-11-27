package com.example.umutguneri.todoapptestapp.Controller;

import com.example.umutguneri.todoapptestapp.R;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.ic_launcher_round);
        setTitle("Welcome");
    }

    /** Called when the user taps the Download and Show Product List button */
    public void callAnotherActivity(View view) {
        // passing to another Activity
        Intent intent = new Intent(this, ShowProductsActivity.class);
        startActivity(intent);
    }

}
