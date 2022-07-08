package com.example.myroomdatabasepractice.UI

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myroomdatabasepractice.Data.User
import com.example.myroomdatabasepractice.Data.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository):ViewModel() {
    val UserList = repository.getuser

    fun insert(user:User){
        viewModelScope.launch(Dispatchers.IO){
            repository.insert(user)
        }
    }
    fun delete(user:User){
        viewModelScope.launch(Dispatchers.IO){
            repository.delete(user)
        }
    }
    fun update(user: User){
        viewModelScope.launch(Dispatchers.IO){
            repository.update(user)
        }
    }

}
