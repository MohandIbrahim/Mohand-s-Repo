package com.test.bostatask.data.models

class PhotosModel : ArrayList<PhotosModel.PhotosModelItem>(){
    data class PhotosModelItem(
        val title: String,
        val url: String
    )
}