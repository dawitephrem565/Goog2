package com.example.filenber.goog;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by filenber on 11/12/2018.
 */

public class CommunitypostCount {
  community_page_iteams community;
    private static final String URL_PRODUCTS = "http://192.168.137.1/gogo/joinchannel.php";
    Community_page_adapter community_page_adapter;
    CommunityFramePage communityFramePage;
    List<community_page_iteams> community_page_iteams;
    int communityid;
    Context context;
    String communityname;
    String uniquecommunityname;
    String creator_name;
    String communityImage;
    String Date;
    String Time;
    String Join_num;
    int id;
   // id,communityid,communityname,uniquename,image);


    public CommunitypostCount(String communityname, String uniquecommunityname, String creator_name, String communityImage, String date, String time, String join_num, int id) {
        this.communityname = communityname;
        this.uniquecommunityname = uniquecommunityname;
        this.creator_name = creator_name;
        this.communityImage = communityImage;
        Date = date;
        Time = time;
        Join_num = join_num;
        this.id = id;
    }


    public void joincommunity (){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_PRODUCTS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.toString().equals("Joined"))
                        {
                            Toast.makeText(context," Joined",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(context,"Not Joined",Toast.LENGTH_LONG).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //    Toast.makeText(getBaseContext(), "Some thing is error", Toast.LENGTH_LONG);
                    }
                }) {
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //params.put("username",Username.getText().toString());
                // int id,int communityid,String communityname,String uniquecommunityname,String image
                params.put("id", "1");
                params.put("communityid","1");
                params.put("communityname","mm");
                params.put("uniquecommunityname","hj,m.");

                params.put("image","");




                //params.put("user", SharedPrefManager.getInstance(getActivity()).getUsername());
                ;
                return params;
            }
        };

    }
}

