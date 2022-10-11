package com.example.tinder

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.tinder.Users.UsersItem
import com.example.tinder.Network.Retrofit
import com.example.tinder.Network.UsersService
import com.example.tinder.Photo.PhotosItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {
    fun getUsers(listUser:MutableLiveData<List<UsersItem>>) {
        val service = Retrofit.retrofit.create(UsersService::class.java)
        service.getUsers().enqueue(object : Callback<List<UsersItem>> {
            override fun onResponse(
                call: Call<List<UsersItem>>,
                response: Response<List<UsersItem>>
            ) {
                listUser.postValue(response.body()!!)
            }

            override fun onFailure(call: Call<List<UsersItem>>, t: Throwable) {
                Log.i("TAG", t.message.toString())
            }

        })
    }


    fun getPhotos(listPhoto:MutableLiveData<List<PhotosItem>>) {
        val service = Retrofit.retrofit.create(UsersService::class.java)
        service.getPhotos().enqueue(object : Callback<List<PhotosItem>> {
            override fun onResponse(
                call: Call<List<PhotosItem>>,
                response: Response<List<PhotosItem>>
            ) {
                listPhoto.postValue(response.body()!!)
            }

            override fun onFailure(call: Call<List<PhotosItem>>, t: Throwable) {
                Log.i("TAG", t.message.toString())
            }

        })
    }


}