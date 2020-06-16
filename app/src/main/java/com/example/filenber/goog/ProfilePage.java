package com.example.filenber.goog;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static com.example.filenber.goog.PostIdea.Request__Permisson_Code;

public class ProfilePage extends AppCompatActivity implements View.OnClickListener {
Button update;
ImageView profileImage;
FloatingActionButton select_image;
    final   static int Request__Permisson_Code = 999;
    private static final String URL_PRODUCTS = "http://192.168.137.1/gogo/update_profile.php";
Bitmap bitmap;
TextInputEditText username , phone , bio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
        username =findViewById(R.id.usernameedittext);
        phone = findViewById(R.id.phoneedittext);
        bio = findViewById(R.id.bioedittext);
        update = findViewById(R.id.update);
        select_image = findViewById(R.id.upload_image_btn);
profileImage = findViewById(R.id.profile_image);
        update.setOnClickListener(this);
       select_image.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == select_image)
        {
            ActivityCompat.requestPermissions(ProfilePage.this,new String[]
                    {
                            Manifest.permission.READ_EXTERNAL_STORAGE },Request__Permisson_Code);
        }
        else if(v == update)
        {
            send_data_to_database();
        }
    }
    private String imagetostring(Bitmap bitmap)
    {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
        byte[] imagebytes = outputStream.toByteArray();
        String encodedImage = Base64.encodeToString(imagebytes,Base64.DEFAULT);
        return encodedImage;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == Request__Permisson_Code)
        {
            if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED)
            {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent,"Select Image "),Request__Permisson_Code);

            }
            else {
                Toast.makeText(getApplicationContext(),"You Dont Have Permission" , Toast.LENGTH_LONG).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == Request__Permisson_Code && resultCode== RESULT_OK&& data!= null)
        {
            Uri file_path = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(file_path);
                bitmap = BitmapFactory.decodeStream(inputStream);
                profileImage.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void send_data_to_database()
    {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_PRODUCTS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext() ,response.toString() ,Toast.LENGTH_LONG).show();

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
                //params.put("communityname", Communityname.getText().toString());
                // params.put("uniquename", uniquenameofcommunity.getText().toString());
                String imagetostring = imagetostring(bitmap);
                params.put("image" , imagetostring);
                params.put("username",username.getText().toString());
                params.put("bio",bio.getText().toString());
                params.put("phone",SharedPrefManager.getInstance(getBaseContext()).getId());
                //  params.put("id", SharedPrefManager.getInstance(getApplicationContext()).getId().toString());
                return params;
            }


        };

        Volley.newRequestQueue(getBaseContext().getApplicationContext()).add(stringRequest);
    }
}
