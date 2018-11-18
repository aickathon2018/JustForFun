package com.example.shopranger.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.shopranger.R;
import com.example.shopranger.model.Person;
import com.example.shopranger.model.Person_;
import com.example.shopranger.model.Style;
import com.example.shopranger.network.ApiClient;
import com.example.shopranger.network.ApiService;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FashionSearchActivity extends AppCompatActivity {

    private FrameLayout progressOverlay;

    private static final String ACCESS_KEY = "468583bdf26b36b48e13";
    private static final String SECRET_KEY = "1642a52c36aee8321e95476c4fe6d524fb439aa6";
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int PICK_IMAGE = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fashion_search);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        progressOverlay = findViewById(R.id.progress_overlay);
        Button takePhoto = findViewById(R.id.take_photo);
        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
            }
        });
        Button choosePhoto = findViewById(R.id.choose);
        choosePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choosePictureFromGallery();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        progressOverlay.setVisibility(View.VISIBLE);
        Bitmap imageBitmap = null;

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            Uri imageUri = data.getData();
            try {
                imageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            assert extras != null;
            imageBitmap = (Bitmap) extras.get("data");
        }

        //create a file to write bitmap data
        File f = new File(this.getCacheDir(), "image.jpg");
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Convert bitmap to byte array
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        assert imageBitmap != null;
        imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        final byte[] bitmapdata = bos.toByteArray();

        //write the bytes in file
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(f);
            fos.write(bitmapdata);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        RequestBody r = RequestBody.create(MediaType.parse("image/*"), f);
        MultipartBody.Part image = MultipartBody.Part.createFormData("filename", f.getName(), r);
        RequestBody accessKey = RequestBody.create(MediaType.parse("text/plain"), ACCESS_KEY);
        RequestBody secretKey = RequestBody.create(MediaType.parse("text/plain"), SECRET_KEY);
        ApiService getResponse = ApiClient.getClient().create(ApiService.class);
        Call<Person> call = getResponse.uploadPhoto(image, accessKey, secretKey);
        call.enqueue(new Callback<Person>() {
            @Override
            public void onResponse(@NonNull Call<Person> call, @NonNull Response<Person> response) {
                try{
                    Person person = response.body();
                    Person_ person_ = person.getPerson();
                    List<Style> styles = person_.getStyles();
                    StringBuilder s = new StringBuilder();
                    for (Style style : styles) {
                        s.append("• ").append(style.getStyleName()).append("\n");
                    }
                    progressOverlay.setVisibility(View.GONE);
                    Intent intent = new Intent(FashionSearchActivity.this, SearchResultActivity.class);
                    intent.putExtra("result", s.toString());
                    intent.putExtra("image", bitmapdata);
                    startActivity(intent);
                }catch (Exception e){
                    progressOverlay.setVisibility(View.GONE);
                    Toast.makeText(FashionSearchActivity.this, "Ooops! Unable to identify the fashion in the photo.\n\nPlease try again", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Person> call, @NonNull Throwable t) {
                progressOverlay.setVisibility(View.GONE);
                Toast.makeText(FashionSearchActivity.this, "Ooops! Unable to identify the fashion in the photo.\nPlease try again", Toast.LENGTH_LONG).show();

            }
        });
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    private void choosePictureFromGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.fashion_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.history) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
