package com.example.myroomdatabasepractice

import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import com.example.myroomdatabasepractice.Data.User
import com.example.myroomdatabasepractice.databinding.ActivityUserAddBinding
import java.util.*

class UserAddActivity : AppCompatActivity() {
    lateinit var binding: ActivityUserAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_user_add)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.adduser -> {
                saveTodo()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun saveTodo() {
        val title = binding.editTextNumber.text.toString()
        val time = binding.timepicker.text.toString()
        //bundle
        val user = User(0,title, time,false)
        val bundle = bundleOf("bundle" to user)
        //Intent
        val intent = Intent()
        intent.putExtra("key",bundle)
        setResult(RESULT_OK,intent)
//        finish()
//        setResult(RESULT_OK,Intent().putExtra("data",user))
        finish()

    }


    fun Opentimepicker(view: View) {
        val calender = Calendar.getInstance()
        val timeListener = TimePickerDialog.OnTimeSetListener{view,hourOfDay,minute ->
            val msg = CheckTime(hourOfDay,minute)
            binding.timepicker.setText(msg)
        }
        val dialog = TimePickerDialog(this,timeListener,calender.get(Calendar.HOUR_OF_DAY),calender.get(Calendar.MINUTE),true)
        dialog.show()
    }

    private fun CheckTime(hourOfDay: Int, minute: Int):String {
        var msg = ""
        if(hourOfDay <10 && minute < 10){msg = "0$hourOfDay:0$minute"}
        else if(hourOfDay <10 && minute > 10){ msg = "0$hourOfDay:$minute"}
        else if(hourOfDay >10 && minute < 10){msg = "$hourOfDay:0$minute"}
        else{msg = "$hourOfDay:$minute"}
        return msg
    }
}