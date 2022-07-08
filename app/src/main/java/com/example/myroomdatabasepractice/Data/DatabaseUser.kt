package com.example.myroomdatabasepractice.Data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Database(entities = [User::class], version = 1)
abstract class DatabaseUser : RoomDatabase() {

    abstract fun userDao(): DaoUser

    companion object{
        @Volatile
        private var INSTANCE:DatabaseUser? =null

        fun getInstance(context: Context):DatabaseUser{
            synchronized(this){
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext,
                        DatabaseUser::class.java,
                        "userdatatest11").build()
                }
                return instance
            }
        }
    }
}

//        fun getDatabase(context: Context, applicationScope: CoroutineScope): DatabaseUser { // this
//            // if the INSTANCE is not null, then return it,
//            // if it is, then create the database
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    DatabaseUser::class.java,
//                    "word_database"
//                ).build()
//                INSTANCE = instance
//                // return instance
//                instance
//            }
//        }