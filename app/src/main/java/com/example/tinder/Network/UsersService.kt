package com.example.tinder.Network


import com.example.tinder.Photo.PhotosItem
import com.example.tinder.Users.UsersItem
import retrofit2.Call
import retrofit2.http.GET

interface UsersService {

    @GET("users")
    fun getUsers():Call<List<UsersItem>>



    @GET("photos")
    fun getPhotos():Call<List<PhotosItem>>
}