package com.test.bostatask.data.repository

import com.test.bostatask.data.local.PreferencesHelper
import com.test.bostatask.data.remote.ApiHelper
import javax.inject.Inject

class MainRepository @Inject constructor (private val preferencesHelper: PreferencesHelper, private val apiHelper: ApiHelper) {

    suspend fun getUsers() = apiHelper.getUsers()
    suspend fun getUserAlbums(userId:Long) = apiHelper.getUserAlbums(userId)
    suspend fun getAlbumPhotos(albumId:Long) = apiHelper.getAlbumPhotos(albumId)
}