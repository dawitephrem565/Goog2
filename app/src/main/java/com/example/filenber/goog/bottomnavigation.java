package com.example.filenber.goog;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.zip.Inflater;

public class bottomnavigation extends AppCompatActivity  implements BottomNavigationView.OnNavigationItemSelectedListener {

SharedPrefManager sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);

        //loading the default fragment
        loadFragment(new CommunityFramePage());
//        Toast.makeText(getBaseContext(),sharedPreferences.getUsername(),Toast.LENGTH_LONG );
        //getting bottom navigation view and attaching the listener
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.community:
                fragment = new CommunityFramePage();
                break;

            case R.id.celebrity:
                fragment = new CommunityFramePage();
                break;

            case R.id.news_provider:
                fragment = new CommunityFramePage();
                break;

            case R.id.tv_entertament:
                fragment = new CommunityFramePage();
                break;



        }

        return loadFragment(fragment);
    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.channel_list_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.channelplus) {
            //SharedPrefManager.getInstance(this).logout();
            // finish();
            startActivity(new Intent(this, Channel_Fragmet_Page.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    */

}
