package com.test.bostatask.data.remote

import javax.inject.Inject

class ApiHelper  @Inject constructor (private val apiServices: ApiServices){

    suspend fun getUsers() = apiServices.getUsers()
    suspend fun getUserAlbums(userId:Long) = apiServices.getUserAlbums(userId)
    suspend fun getAlbumPhotos(albumId:Long) = apiServices.getAlbumPhotos(albumId)
}