package com.example.filenber.goog;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by filenber on 08/12/2018.
 */

public class Channel_Fragmet_Page extends AppCompatActivity {
    View view;
    RecyclerView channelRecycleview;
    Channel_list_adapter channel_list_adapter;
    List<channel_list_items> channel_list_items;
    private static final String URL_PRODUCTS = "http://192.168.137.1/gogo/channel.php";
LinearLayoutManager linearLayoutManager;
Button btn;
Toolbar toolbar;
        @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.channellis_recyleview);
        channel_list_items = new ArrayList<>();

        channelRecycleview = (RecyclerView)findViewById(R.id.channellist_recycleview);
btn= (Button)findViewById(R.id.createnewcommunity) ;

            channel_list_adapter= new Channel_list_adapter(getBaseContext(),channel_list_items);
        loadCommunityPosts();


         channelRecycleview.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));

         channelRecycleview.setHasFixedSize(true);


    }
    public void createcommunity(View v)
    {
        Intent intent = new Intent(Channel_Fragmet_Page.this,CreateChannel.class);
        startActivity(intent);
    }

    private void loadCommunityPosts() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_PRODUCTS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //
//                              String val = getArguments().getString("mykey");
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);

                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject product = array.getJSONObject(i);

                                //adding the product to product list

                                channel_list_items.add(new channel_list_items(
                                        product.getInt("id"),
                                        product.getString("communityname"),
                                        product.getString("uniquecommunityname"),
                                        product.getString("creator_name"),
                                        product.getString("communityImage"),
                                        product.getString("Date"),
                                        product.getString("Time"),
                                        product.getString("Join_num"),
                                        product.getString("status")


                                ));
                            }

                            //creating adapter object and setting it to recyclerview
                            Channel_list_adapter adapter = new Channel_list_adapter(getBaseContext(), channel_list_items);
                            channelRecycleview.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getBaseContext(), "Some thing is error", Toast.LENGTH_LONG);
                    }
                }) {
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
            //    params.put("userid",.getText().toString());

                //params.put("user", SharedPrefManager.getInstance(getActivity()).getUsername());
                ;
                return params;
            }
        };

        //adding our stringrequest to queue
        Volley.newRequestQueue(getApplicationContext().getApplicationContext()).add(stringRequest);
    }




 public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.channel_list_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        //String message = "You click fragment ";

        if(itemId == R.id.create_channel_btn)
        {

        }



        return super.onOptionsItemSelected(item);
    }


}

