package com.example.filenber.goog;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by filenber on 07/12/2018.
 */

public class Channel_list_adapter extends RecyclerView.Adapter<Channel_list_adapter.ChannelViewHolder> {

Context context;
CommunitypostCount communitypostCount;
List<channel_list_items> channel_list;
channel_list_items channel_list_items;
Channel_list_adapter channel_list_adapter;

    public Channel_list_adapter(Context mCtx, List<channel_list_items> channel_list_items) {
        this.context = mCtx;
        this.channel_list = channel_list_items;
    }

    @NonNull
    @Override
    public ChannelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.channellist_iteam_list, null);
        return new ChannelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ChannelViewHolder holder, int position) {
        final channel_list_items product = channel_list.get(position);
        holder.channelname.setText(product.getCommunityname());
        holder.communityid.setText(String.valueOf(product.communityid));


        Glide.with(context).load(product.getCommunityImage()).into(holder.imageView);

        holder.channeluniqename.setText(product.getUniquecommunityname());
        if (product.getStatus().toString().equals("true"))
        {

           holder.join.setVisibility(View.INVISIBLE);
           holder.remove_join.setVisibility(View.VISIBLE);

        }
        else
        {
            holder.remove_join.setVisibility(View.INVISIBLE);
            holder.join.setVisibility(View.VISIBLE);
        }
 holder.remove_join.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View v) {
         StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://192.168.137.1/gogo/removejoinchannel.php",
                 new Response.Listener<String>() {
             @Override
             public void onResponse(String response) {
                 try {
                     JSONObject object = new JSONObject(response);
                     if (object.getBoolean("Success"))
                     {
                         Toast.makeText(context,"You Removed  " + product.getCommunityname() + "  Community" ,Toast.LENGTH_LONG).show();
                        holder.remove_join.setVisibility(View.INVISIBLE);
                        holder.join.setVisibility(View.VISIBLE);
                     }

                 } catch (JSONException e) {
                     e.printStackTrace();
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
                 params.put("id",SharedPrefManager.getInstance(context).getId());
                 params.put("communityid",String.valueOf(product.getCommunityid()));
               //  params.put("communityname",product.getCommunityname());
               //  params.put("uniquecommunityname",product.getUniquecommunityname());

                 //params.put("image",product.getCommunityImage());




                 //params.put("user", SharedPrefManager.getInstance(getActivity()).getUsername());
                 ;
                 return params;
             }
         };

         Volley.newRequestQueue(context.getApplicationContext()).add(stringRequest);
//                 communitypostCount.joincommunity();
         //communitypostCount.Joincommunity(1,1,"dave","ethiopia","im");


     }
 });
        holder.join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  String ids= SharedPrefManager.getInstance(getBaseContext()).getId().toString();
            //    final int id = Integer.parseInt(ids);


                StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://192.168.137.1/gogo/joinchannel.php",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject object = new JSONObject(response);
                                    if(object.getBoolean("Join"))
                                    {
                                        Toast.makeText(context,"You  Joined  " + product.getCommunityname() + " Community",Toast.LENGTH_LONG).show();
                                        holder.join.setVisibility(View.INVISIBLE);
                                        holder.remove_join.setVisibility(View.VISIBLE);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
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
                        params.put("id",SharedPrefManager.getInstance(context).getId());
                        params.put("communityid",String.valueOf(product.getCommunityid()));
                        params.put("communityname",product.getCommunityname());
                        params.put("uniquecommunityname",product.getUniquecommunityname());

                        params.put("image",product.getCommunityImage());




                        //params.put("user", SharedPrefManager.getInstance(getActivity()).getUsername());
                        ;
                        return params;
                    }
                };

                Volley.newRequestQueue(context.getApplicationContext()).add(stringRequest);
//                 communitypostCount.joincommunity();
                //communitypostCount.Joincommunity(1,1,"dave","ethiopia","im");


            }
        });
    }

    @Override
    public int getItemCount() {
        return channel_list.size();
    }

    public class ChannelViewHolder extends RecyclerView.ViewHolder {
        TextView channelname;
        TextView channeluniqename , creatorname , date,time,join_num ,communityid;
        ImageView imageView;
        Button join,remove_join;

        public ChannelViewHolder(View itemView) {
            super(itemView);
            channelname = (TextView)itemView.findViewById(R.id.channel_name);
            channeluniqename = (TextView)itemView.findViewById(R.id.channel_uniqename);
            imageView = (ImageView)itemView.findViewById(R.id.channel_profile_page);
            creatorname = (TextView)itemView.findViewById(R.id.creatorname);
            date = (TextView)itemView.findViewById(R.id.Date);
            communityid = (TextView)itemView.findViewById(R.id.community_id);
            time = (TextView)itemView.findViewById(R.id.Time);
            join_num = (TextView)itemView.findViewById(R.id.Join_num);
            join=(Button)itemView.findViewById(R.id.join);
            remove_join = (Button)itemView.findViewById(R.id.join_remove);

        }
    }
}
