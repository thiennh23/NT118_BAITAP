package com.example.sampleproject.Model;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("realm")
    public String realm;

    @SerializedName("id")
    public String id;

    @SerializedName("firstName")
    public String firstName;

    @SerializedName("lastName")
    public String lastName;
    @SerializedName("email")
    public String email;
    @SerializedName("enabled")
    public String enabled;
    @SerializedName("createdOn")
    public String createdOn;
    @SerializedName("secret")
    public String secret;
    @SerializedName("attributes")
    public Object attributes;

    @SerializedName("serviceAccount")
    public boolean serviceAccount;

    @SerializedName("username")
    public String username;
}
