package com.example.shopranger.network;

import com.example.shopranger.model.Person;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiService {
    @Multipart
    @POST("/analyze")
    Call<Person> uploadPhoto(@Part MultipartBody.Part image,
                             @Part("access_key") RequestBody accessKey,
                             @Part("secret_key") RequestBody secretKey);
}
