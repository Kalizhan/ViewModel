package com.example.kotlinproject

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel: ViewModel() {

    var userList: MutableLiveData<List<User>> = MutableLiveData()

    init {
        userList.value = UserData.getUsers()
    }

    fun getListUsers() = userList

    fun updateListUsers(){
        //userList.value = UserData.getAnotherUsers()

        userList.value = UserData.getUsers()?.filter { it.age <=18 }
//        userList.value?.filter { age: User ->
//            age.age >=18
//        }
    }
}