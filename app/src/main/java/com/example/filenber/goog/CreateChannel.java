package com.example.filenber.goog;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class CreateChannel extends AppCompatActivity  {
CircleImageView profileImage;
Context context;
TextInputEditText Communityname , uniquenameofcommunity;
SharedPrefManager sharedprefmanager;
    public static final String URL_CommunityAdd = "http://192.168.137.1/gogo/channelcreate.php";
Button createcommunitybtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_channel);
        profileImage = (CircleImageView)findViewById(R.id.channel_profile_page);
        Communityname = (TextInputEditText)findViewById(R.id.communityname);
        uniquenameofcommunity = (TextInputEditText)findViewById(R.id.uniquenameofcommunity);
        createcommunitybtn = (Button)findViewById(R.id.create_community_btn);
//       sharedprefmanager.getUsername();

    }

   public void createcommunity(View v)
   {
       StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_CommunityAdd, new Response.Listener<String>() {
           @Override
           public void onResponse(String response) {
               try {
                   JSONObject jsonObject = new JSONObject(response);
                   if (jsonObject.getBoolean("create"))
                   {
                       Toast.makeText(getBaseContext(),"Community  Created",Toast.LENGTH_LONG).show();
                   }
                   else
                   {
                       Toast.makeText(getBaseContext(),"Community  already Created",Toast.LENGTH_LONG).show();
                   }
               } catch (JSONException e) {
                   e.printStackTrace();
               }
             //  Toast.makeText(getBaseContext(),response.toString(),Toast.LENGTH_LONG).show();
           }


       },
       new Response.ErrorListener() {
       @Override
       public void onErrorResponse(VolleyError error) {


           Toast.makeText(
                   getApplicationContext(),
                   error.toString(),
                   Toast.LENGTH_LONG
           ).show();
       }
   })
       {
           @Override
           protected Map<String, String> getParams() throws AuthFailureError {
               Map<String, String> params = new HashMap<>();
               params.put("communityname", Communityname.getText().toString());
               params.put("uniquename", uniquenameofcommunity.getText().toString());
               params.put("id", SharedPrefManager.getInstance(context).getId().toString());
               return params;
           }


       };

       Volley.newRequestQueue(getBaseContext().getApplicationContext()).add(stringRequest);
   }
}
