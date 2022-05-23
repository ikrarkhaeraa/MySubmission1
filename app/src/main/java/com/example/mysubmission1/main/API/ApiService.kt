package com.example.mysubmission1.main.API

import com.example.mysubmission1.main.response.AddNewStoryResponse
import com.example.mysubmission1.main.response.GetAllStoryResponse
import com.example.mysubmission1.main.response.LoginResponse
import com.example.mysubmission1.main.response.RegisterResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @FormUrlEncoded
    @POST("register")
    fun uploadDataRegis(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<RegisterResponse>

    @FormUrlEncoded
    @POST("login")
    fun uploadDataLogin(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<LoginResponse>

    @GET("stories")
    fun getStory(
        @Header("Authorization") token: String,
    ): Call<GetAllStoryResponse>

    @Multipart
    @POST("stories")
    fun uploadStory(
        @Header("Authorization") token: String,
        @Part file: MultipartBody.Part,
        @Part("description") description: RequestBody
    ):Call<AddNewStoryResponse>
}