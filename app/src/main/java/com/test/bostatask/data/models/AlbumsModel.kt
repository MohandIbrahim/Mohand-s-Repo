package com.test.bostatask.data.models

class AlbumsModel : ArrayList<AlbumsModel.AlbumsModelItem>(){
    data class AlbumsModelItem(
        val id: Long,
        val title: String
    )
}