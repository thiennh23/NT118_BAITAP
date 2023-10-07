package com.example.sampleproject;

import com.example.sampleproject.Model.Asset;
import com.example.sampleproject.Model.User;
import com.example.sampleproject.Model.Status;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface APIInterface {

    @GET("api/master/asset/{assetID}")
    Call<Asset> getAsset(@Path("assetID") String assetID);//, @Header("Authorization") String auth);

    @GET("api/master/user/user")
    Call<User> getUser(); // Add the return type for getUserAsset

    @GET("api/master/info")
    Call<Status> getInfor(); // Add the return type for getUserAsset
}
