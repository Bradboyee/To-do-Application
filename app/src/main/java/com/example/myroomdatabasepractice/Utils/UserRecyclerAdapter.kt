package com.example.myroomdatabasepractice.Utils

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myroomdatabasepractice.Data.User
import com.example.myroomdatabasepractice.R
import kotlinx.android.synthetic.main.user_item.view.*

class UserRecyclerAdapter(private val onCheckListener: onCheckListener):RecyclerView.Adapter<UserRecyclerAdapter.UserViewHolder>() {
    var userlist = listOf<User>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemview = LayoutInflater.from(parent.context).inflate(R.layout.user_item,parent,false)
        return UserViewHolder(itemview)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val current = userlist[position]
        holder.bind(current,onCheckListener)
    }

    override fun getItemCount(): Int =userlist.size

    fun submitlist(list:List<User>){
        this.userlist = list
    }


    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titletextview = itemView.title
        val timetextview = itemView.time
        val checkbox = itemView.checkBox

        fun bind (user:User,action: onCheckListener){
            titletextview.text = user.title
            timetextview.text = user.time
            checkbox.isChecked = user.isComplete

            if(checkbox.isChecked){titletextview.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG}

            checkbox.setOnCheckedChangeListener{buttonView,isChecked ->
                if(isChecked){action.onCheckBox(adapterPosition,true)}
                else{action.onCheckBox(adapterPosition,false)}
            }
        }
    }
}

interface onCheckListener{
    fun onCheckBox(position: Int,state:Boolean)
}