package com.test.bostatask.data.models

class UsersModel : ArrayList<UsersModel.UsersModelItem>(){
    data class UsersModelItem(
        val address: Address,
        val id: Long,
        val name: String,
    ){
        data class Address(
            val city: String,
            val street: String,
            val suite: String,
            val zipcode: String
        )
    }
}