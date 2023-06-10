package com.test.bostatask.data.remote

import com.test.bostatask.data.models.AlbumsModel
import com.test.bostatask.data.models.PhotosModel
import com.test.bostatask.data.models.UsersModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices
{
    @GET("users/")
    suspend fun getUsers(): UsersModel

    @GET("albums/")
    suspend fun getUserAlbums(@Query ("userId") userID: Long ): AlbumsModel

    @GET("photos/")
    suspend fun getAlbumPhotos(@Query("albumId") albumId: Long ): PhotosModel

}