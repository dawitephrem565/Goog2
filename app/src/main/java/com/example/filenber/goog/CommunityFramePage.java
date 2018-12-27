package com.example.filenber.goog;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import android.support.v4.app.Fragment;
import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.request.RequestOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class CommunityFramePage extends Fragment implements View.OnClickListener {
    View view;
    Context context = getActivity();
    RecyclerView communityRecycleview;
    Community_page_adapter community_page_adapter;
    List<community_page_iteams> community_page_iteams;
    FloatingActionButton postbtn;
    private static final String URL_PRODUCTS = "http://192.168.137.1/gogo/post.php";



    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.communityrecycleview,container,false);
        communityRecycleview = view.findViewById(R.id.communityrecyleview);

        postbtn = view.findViewById(R.id.communityfab);
        postbtn.setOnClickListener(this);
        community_page_adapter = new Community_page_adapter(getActivity(),community_page_iteams);
        communityRecycleview.setAdapter(community_page_adapter);
        communityRecycleview.setLayoutManager(new StaggeredGridLayoutManager(1,LinearLayoutManager.VERTICAL));
        return view;



    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        community_page_iteams = new ArrayList<>();

        loadCommunityPosts();

    }

    public void loadCommunityPosts() {

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

                                community_page_iteams.add(new community_page_iteams(

                                        product.getInt("id"),
                                        product.getString("username"),
                                        product.getString("userprofile_img"),
                                        product.getString("uniq_channel_name"),
                                        product.getString("userpost_msg"),
                                        product.getString("userimgpost"),
                                        product.getString("userid"),
                                        product.getString("numberofclap"),
                                        product.getString("numberofcomment"),
                                        product.getString("Date"),
                                         product.getString("Time")


                                ));
                            }

                            //creating adapter object and setting it to recyclerview
                            Community_page_adapter adapter = new Community_page_adapter(getContext(), community_page_iteams);
                            communityRecycleview.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());
              //  progressDialog.dismiss();
            }
        }) {
            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                try {
                    Cache.Entry cacheEntry = HttpHeaderParser.parseCacheHeaders(response);
                    if (cacheEntry == null) {
                        cacheEntry = new Cache.Entry();
                    }
                    final long cacheHitButRefreshed = 3 * 60 * 1000; // in 3 minutes cache will be hit, but also refreshed on background
                    final long cacheExpired = 24 * 60 * 60 * 1000; // in 24 hours this cache entry expires completely
                    long now = System.currentTimeMillis();
                    final long softExpire = now + cacheHitButRefreshed;
                    final long ttl = now + cacheExpired;
                    cacheEntry.data = response.data;
                    cacheEntry.softTtl = softExpire;
                    cacheEntry.ttl = ttl;
                    String headerValue;
                    headerValue = response.headers.get("Date");
                    if (headerValue != null) {
                        cacheEntry.serverDate = HttpHeaderParser.parseDateAsEpoch(headerValue);
                    }
                    headerValue = response.headers.get("Last-Modified");
                    if (headerValue != null) {
                        cacheEntry.lastModified = HttpHeaderParser.parseDateAsEpoch(headerValue);
                    }
                    cacheEntry.responseHeaders = response.headers;
                    final String jsonString = new String(response.data,
                            HttpHeaderParser.parseCharset(response.headers));
                    return Response.success((jsonString), cacheEntry);
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                }
            }



            @Override
            public void deliverError(VolleyError error) {
                super.deliverError(error);
            }

            @Override
            protected VolleyError parseNetworkError(VolleyError volleyError) {
                return super.parseNetworkError(volleyError);
            }
        };

        //adding our stringrequest to queue
        Volley.newRequestQueue(getActivity().getBaseContext()).add(stringRequest);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.channeladdmenu, menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        //String message = "You click fragment ";

        if(itemId == R.id.channel_add_menu)
        {
          Intent intent = new Intent(getContext(),Channel_Fragmet_Page.class);
          startActivity(intent);
        }
        else if(itemId==R.id.profilesetting)
        {
            Intent intent = new Intent(getContext(),ProfilePage.class);
            startActivity(intent);
        }
 else if(itemId==R.id.logoutbtn)
        {
            Intent intent = new Intent(CommunityFramePage.this.context,Login_activity.class);
            startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {
      int id = v.getId();
      switch (id)
      {
          case R.id.communityfab:
          {
           Intent intent = new Intent(CommunityFramePage.super.getContext(),PostIdea.class);
           startActivity(intent);
          }
      }
    }



}
