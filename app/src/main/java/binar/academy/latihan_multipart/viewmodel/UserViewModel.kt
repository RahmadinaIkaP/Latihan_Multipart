package binar.academy.latihan_multipart.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import binar.academy.latihan_multipart.data.model.UserResponse
import binar.academy.latihan_multipart.data.network.RetrofitClient
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel : ViewModel() {

    private var liveDataUser : MutableLiveData<UserResponse?> = MutableLiveData()

    fun registerObserver() : MutableLiveData<UserResponse?> = liveDataUser

    fun register(fullName : RequestBody, email : RequestBody, password : RequestBody, phoneNumber : RequestBody, address : RequestBody, image : MultipartBody.Part, city : RequestBody){
        RetrofitClient.instance.register(fullName, email, password, phoneNumber, address, image, city)
            .enqueue(object : Callback<UserResponse>{
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    if (response.isSuccessful){
                       liveDataUser.postValue(response.body())
                    }else{
                        liveDataUser.postValue(null)
                        Log.d("msg", response.message())
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    liveDataUser.postValue(null)
                }

            })
    }
}