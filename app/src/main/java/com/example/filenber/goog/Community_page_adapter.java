package com.example.filenber.goog;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by filenber on 03/12/2018.
 */

public class Community_page_adapter extends RecyclerView.Adapter<Community_page_adapter.ProductViewHolder> {

CommunitypostCount communitypostCount;
    private Context mCtx;
    private List<community_page_iteams> community_page_iteams_list;

    public Community_page_adapter(Context context, List<community_page_iteams> community_page_iteams) {
    mCtx=context;
    community_page_iteams_list=community_page_iteams;
    }


    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.community_list_iteam, null);
        return new ProductViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, int position) {
        final community_page_iteams product = community_page_iteams_list.get(position);

        //loading the image
                holder.username.setText(product.getUsername());
        Glide.with(mCtx ).load(product.getUserprofileimage()).into(holder.userprofileImage);

        holder.where_is_from.setText(product.getUserworkarea());
     holder.usercomment.setText(product.getUsername());
        holder.userposttime.setText(product.getTime());
        holder.numberofclap.setText(product.getNumberofclap());
        holder.numberofcomment.setText(product.getNumberofcomment());
        holder.clapstatus.setText(product.getClapstatus());
        Glide.with(mCtx ).load(product.getUserprofileimage()).into(holder.userpostimage);
        if( holder.clapstatus.getText().equals("true"))
        {
            holder.clapbtn.setVisibility(View.INVISIBLE);
            holder.redbtn.setVisibility(View.VISIBLE);
        }

       /* if(communitypostCount.stringRequest.equals("Yes"));
        {
            String currentnumclap = holder.numberofclap.getText().toString();

            int numvalue=Integer.parseInt(currentnumclap);
            int total  = numvalue + 1;
            holder.numberofclap.setText(Integer.toString(total));
           // CommunitypostCount communitypostCount = new CommunitypostCount();
          //  communitypostCount.countclapadd(numvalue);
            String displayid = Integer.toString(product.getId());
            Toast.makeText(mCtx,displayid.toString(),Toast.LENGTH_LONG).show();
            holder.clapbtn.setVisibility(View.INVISIBLE);
        }*/

/*holder.clapbtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        //holder.clapbtn.setBackgroundResource(R.drawable.redclap);
        String currentnumclap = holder.numberofclap.getText().toString();

        int numvalue=Integer.parseInt(currentnumclap);
        int total  = numvalue + 1;
        holder.numberofclap.setText(Integer.toString(total));
        CommunitypostCount communitypostCount = new CommunitypostCount();
        communitypostCount.countclapadd(numvalue);
        String displayid = Integer.toString(product.getId());
        Toast.makeText(mCtx,displayid.toString(),Toast.LENGTH_LONG).show();
       holder.clapbtn.setVisibility(View.INVISIBLE);

holder.redbtn.setVisibility(View.VISIBLE);
    }
});
holder.redbtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String currentnumclap = holder.numberofclap.getText().toString();
        int numvalue=Integer.parseInt(currentnumclap);
        int total  = numvalue - 1;
        holder.numberofclap.setText(Integer.toString(total));
        holder.redbtn.setVisibility(View.INVISIBLE);
        holder.clapbtn.setVisibility(View.VISIBLE);
    }
});
*/

        //Intent

    }
    public void clicked(final ProductViewHolder holder)
    {
        holder.redbtn.setVisibility(View.VISIBLE);
        holder.clapbtn.setVisibility(View.INVISIBLE);
    }
    public void notclicked(final ProductViewHolder holder)
    {
        holder.clapbtn.setVisibility(View.VISIBLE);
        holder.redbtn.setVisibility(View.INVISIBLE);
    }

  //e2135 hd power earehon ubl ;
    @Override
    public int getItemCount() {
            return community_page_iteams_list.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView username, where_is_from, usercomment, userposttime,numberofclap,numberofcomment,clapstatus;
        ImageView userpostimage;
        CircleImageView userprofileImage;
        Button clapbtn,commentbtn, redbtn;

        public ProductViewHolder(View itemView) {
            super(itemView);

            username = (TextView)itemView.findViewById(R.id.textView2);
            where_is_from=(TextView)itemView.findViewById(R.id.textView);
            usercomment =(TextView)itemView.findViewById(R.id.textView3);
            userposttime=(TextView)itemView.findViewById(R.id.userposttime);
            numberofclap=(TextView)itemView.findViewById(R.id.numberofclap);
            numberofcomment=(TextView)itemView.findViewById(R.id.numberofcomment);
            clapstatus = (TextView)itemView.findViewById(R.id.clapstatus);
            userpostimage =(ImageView)itemView.findViewById(R.id.imageView);
            userprofileImage=(CircleImageView) itemView.findViewById(R.id.circleImageView);
            redbtn = (Button) itemView.findViewById(R.id.redclap);
            clapbtn=(Button) itemView.findViewById(R.id.readyclap);
            commentbtn=(Button) itemView.findViewById(R.id.commentimage);



        }
    }
}