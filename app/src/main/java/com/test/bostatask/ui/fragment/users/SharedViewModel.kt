package com.test.bostatask.ui.fragment.users

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.agent.common.utils.Resource
import com.test.bostatask.data.models.AlbumsModel
import com.test.bostatask.data.models.PhotosModel
import com.test.bostatask.data.models.UsersModel
import com.test.bostatask.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@HiltViewModel
class SharedViewModel @Inject constructor(private val mainRepository: MainRepository) :
    ViewModel() {

    private lateinit var userList: ArrayList<UsersModel.UsersModelItem>
    private val _usersVisitList = MutableLiveData<ArrayList<UsersModel.UsersModelItem>>()
    val usersVisitList: LiveData<ArrayList<UsersModel.UsersModelItem>> get() = _usersVisitList

    private lateinit var albumsList: ArrayList<AlbumsModel.AlbumsModelItem>
    private val getAlbumsResponse = MutableLiveData<ArrayList<AlbumsModel.AlbumsModelItem>>()

    private lateinit var photosList: ArrayList<PhotosModel.PhotosModelItem>
    private val getPhotosResponse = MutableLiveData<ArrayList<PhotosModel.PhotosModelItem>>()

    init {
        getUsers()
    }

    private fun getUsers() {
        userList = ArrayList()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val usersList = mainRepository.getUsers()
                if (usersList != null) {
                    for (index in 0 until usersList.size) {
                        this@SharedViewModel.userList.add(
                            this@SharedViewModel.userList.size,
                            UsersModel.UsersModelItem(
                                usersList[index].address,
                                usersList[index].id,
                                usersList[index].name
                            )
                        )
                    }
                    Log.e("Array", this@SharedViewModel.userList.size.toString())
                    _usersVisitList.postValue(this@SharedViewModel.userList)
                }
            } catch (e: Exception) {
                Log.d("catch", e.message.toString())

            }
        }
    }

     fun getAlbums(userId: Long) : LiveData<ArrayList<AlbumsModel.AlbumsModelItem>> {
        albumsList = ArrayList()

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = mainRepository.getUserAlbums(userId)
                if (response.isNotEmpty()) {
                    for (index in 0 until response.size) {
                        albumsList.add(albumsList.size,
                            AlbumsModel.AlbumsModelItem(
                                response[index].id,
                                response[index].title
                            )
                        )
                    }
                    getAlbumsResponse.postValue(albumsList)
                }
            } catch (e: Exception) {
             Log.e("TAG",e.toString())
            }
        }
        return getAlbumsResponse
    }

    fun getPhotos(albumId: Long) : LiveData<ArrayList<PhotosModel.PhotosModelItem>> {
        photosList = ArrayList()

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = mainRepository.getAlbumPhotos(albumId)
                if (response.isNotEmpty()) {
                    for (index in 0 until response.size) {
                        photosList.add(photosList.size,
                            PhotosModel.PhotosModelItem(
                                response[index].title,
                                response[index].thumbnailUrl
                            )
                        )
                    }
                    getPhotosResponse.postValue(photosList)
                }
            } catch (e: Exception) {
                Log.e("TAG",e.toString())
            }
        }
        return getPhotosResponse
    }
}