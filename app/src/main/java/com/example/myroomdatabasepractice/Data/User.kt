package com.example.myroomdatabasepractice.Data

import android.os.Parcelable
import android.widget.EditText
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "Todo_Table")
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "todoId")
    val id: Int,
    val title: String,
    val time: String,
    val isComplete: Boolean
):Parcelable