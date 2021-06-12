package com.example.kotlinproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_second.*
import java.util.*

class MainActivity : AppCompatActivity() {

    //инициализируем ViewModel ленивым способом
    private val userViewModel by lazy { ViewModelProviders.of(this).get(UserViewModel::class.java)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //инициализируем адаптер и присваиваем его списку
        val adapter = UserAdapter()
        userList.layoutManager = LinearLayoutManager(this)
        userList.adapter = adapter

        //подписываем адаптер на изменения списка
        userViewModel.getListUsers().observe(this, androidx.lifecycle.Observer {
            it?.let {
                adapter.refreshUsers(it)
            }
        })
    }

    //создаем меню
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    //при нажатии пункта меню Refresh обновляем список
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId) {
            R.id.refresh -> {
                userViewModel.updateListUsers()
            }
        }
        return super.onOptionsItemSelected(item)
    }

//    fun toastFun(view: View){
//        Toast.makeText(this, "Hello, User!", Toast.LENGTH_SHORT).show()
//    }
//
//    fun countFun(view: View){
//        val countString = textView.text.toString()
//
//        var count: Int = Integer.parseInt(countString)
//        count++
//
//        textView.text = count.toString()
//    }
//
//    fun randromFun(view: View){
//        val randomIntent = Intent(this, SecondActivity::class.java)
//
//        val countString = textView.text.toString()
//
//        val count = Integer.parseInt(countString)
//
//        randomIntent.putExtra(SecondActivity.TOTAL_COUNT, count)
//
//        startActivity(randomIntent)
//    }
}