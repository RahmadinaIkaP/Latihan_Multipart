package binar.academy.latihan_multipart.data.network

import binar.academy.latihan_multipart.data.model.UserResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {

    @POST("auth/register")
    @Multipart
    fun register(
        @Part("full_name") fullName : RequestBody,
        @Part("email") email : RequestBody,
        @Part("password") password : RequestBody,
        @Part("phone_number") phoneNumber : RequestBody,
        @Part("address") address : RequestBody,
        @Part image : MultipartBody.Part,
        @Part("city") city : RequestBody
    ) : Call<UserResponse>
}