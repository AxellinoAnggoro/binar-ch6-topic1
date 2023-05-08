package com.axellinoanggoro.binar_ch6_topic1.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.axellinoanggoro.binar_ch6_topic1.R
import com.axellinoanggoro.binar_ch6_topic1.adapter.UserAdapter
import com.axellinoanggoro.binar_ch6_topic1.databinding.ActivityMainBinding
import com.axellinoanggoro.binar_ch6_topic1.model.GetUserResponseItem
import com.axellinoanggoro.binar_ch6_topic1.network.ApiClient
import com.axellinoanggoro.binar_ch6_topic1.viewmodel.UserViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getDataUser()

    }

    fun getDataUser() {
        ApiClient.instance.getAllUser().enqueue(object : Callback<List<GetUserResponseItem>> {
            override fun onResponse(
                call: Call<List<GetUserResponseItem>>,
                response: Response<List<GetUserResponseItem>>
            ) {
                if (response.isSuccessful) {
                    //tanpa viewmodel
//                    binding.rvNews.layoutManager =
//                        LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
//                        binding.rvNews.adapter = NewsAdapter(response.body()!!)
                    showDataUser()
                } else {
                    Toast.makeText(this@MainActivity, "Failed to load data", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<List<GetUserResponseItem>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Fail", Toast.LENGTH_SHORT).show()
            }

        })
    }

    fun showDataUser(){
        val viewModelNews = ViewModelProvider(this@MainActivity).get(UserViewModel::class.java)
        viewModelNews.callApi()
        viewModelNews.liveDataUser.observe(this@MainActivity, Observer {
            if (it != null){
                binding.rvUser.layoutManager = LinearLayoutManager(this@MainActivity,
                    LinearLayoutManager.VERTICAL, false)
                binding.rvUser.adapter = UserAdapter(it)
            }
        })
    }
}