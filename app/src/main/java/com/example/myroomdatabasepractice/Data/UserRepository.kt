package com.example.myroomdatabasepractice.Data

class UserRepository (private val dao:DaoUser) {
    val getuser = dao.getUser()

    suspend fun insert(user:User){
        return dao.insertuser(user)
    }

    suspend fun delete(user: User){
        return dao.deleteuser(user)
    }

    suspend fun update(user: User){
        return dao.updateuser(user)
    }
}