package com.example.filenber.goog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Thread thread = new Thread(){

            @Override
            public void run() {

                try
                {
                    sleep(1000);
                    Intent intent = new Intent(MainActivity.this,Login_activity.class);
                    startActivity(intent);
                    finish();

                }
                catch (Exception ex)
                {

                }
            }
        };
        thread.start();
    }

}
