package com.example.myroomdatabasepractice

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract.Helpers.insert
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myroomdatabasepractice.Data.DatabaseUser
import com.example.myroomdatabasepractice.Data.User
import com.example.myroomdatabasepractice.Data.UserRepository
import com.example.myroomdatabasepractice.UI.UserViewModel
import com.example.myroomdatabasepractice.UI.UserViewModelFactory
import com.example.myroomdatabasepractice.Utils.UserRecyclerAdapter
import com.example.myroomdatabasepractice.Utils.onCheckListener
import com.example.myroomdatabasepractice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),onCheckListener {
    lateinit var binding: ActivityMainBinding //add <layout> at xml file first
    lateinit var userRecyclerAdapter: UserRecyclerAdapter
    lateinit var userViewModel: UserViewModel

    val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val bundle = result.data!!.getBundleExtra("key")
            val dataobject = bundle?.getParcelable<User>("bundle")
            Log.i("item",dataobject.toString())
            if (dataobject != null) {
                userViewModel.insert(dataobject)
            }
            // Handle the Intent
        }
    }

    override fun onCheckBox(position: Int, state: Boolean) {
        val item = userViewModel.UserList.value!![position]
        userViewModel.update(User(item.id,item.title,item.time,state))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        initUI()
    }

    private fun initUI() {
        val dao = DatabaseUser.getInstance(this).userDao()
        val repository = UserRepository(dao)
        val factory = UserViewModelFactory(repository)
        userViewModel = ViewModelProvider(this,factory)[UserViewModel::class.java]
        //RecyclerView
        userRecyclerAdapter = UserRecyclerAdapter(this)
        binding.myrecyclerview.layoutManager = LinearLayoutManager(this)
        observeData()

        binding.floatingActionButton2.setOnClickListener {
            val intent = Intent(this,UserAddActivity::class.java)
//            startActivityForResult(intent,REQUEST_ADD)
            startForResult.launch(intent)
        }
        swipeHeler()
    }

    private fun swipeHeler() {
        val itemTouchHelperSimpleCallback = object : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder,
            ): Boolean {
                TODO("Not yet implemented")
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                userViewModel.delete(userViewModel.UserList.value!![viewHolder.adapterPosition])
            }
        }
        val itemTouchHelper = ItemTouchHelper(itemTouchHelperSimpleCallback)
        itemTouchHelper.attachToRecyclerView(binding.myrecyclerview)
    }


    private fun observeData() {
        userViewModel.UserList.observe(this, {
            userRecyclerAdapter.submitlist(it)
            binding.myrecyclerview.adapter = userRecyclerAdapter
        })
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if(requestCode == REQUEST_ADD && resultCode == REQUEST_ADD){
//            val bundle = data!!.getBundleExtra("key")
//            val todo = bundle!!.getParcelable<User>("bundle")
//            userViewModel.insert(todo!!)
//        }
//    }
//
//    companion object{
//        val REQUEST_ADD = 1
//    }
}