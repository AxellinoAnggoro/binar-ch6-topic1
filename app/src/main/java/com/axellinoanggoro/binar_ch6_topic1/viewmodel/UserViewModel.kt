package com.axellinoanggoro.binar_ch6_topic1.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.axellinoanggoro.binar_ch6_topic1.model.GetUserResponseItem
import com.axellinoanggoro.binar_ch6_topic1.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel : ViewModel() {
    var liveDataUser: MutableLiveData<List<GetUserResponseItem>> = MutableLiveData()

    fun callApi() {
        ApiClient.instance.getAllUser().enqueue(object : Callback<List<GetUserResponseItem>> {
            override fun onResponse(
                call: Call<List<GetUserResponseItem>>,
                response: Response<List<GetUserResponseItem>>
            ) {
                if (response.isSuccessful) {
                    liveDataUser.postValue(response.body())
                } else {
                    liveDataUser.postValue(null)
                }
            }

            override fun onFailure(call: Call<List<GetUserResponseItem>>, t: Throwable) {
                liveDataUser.postValue(null)

            }
        })
    }
}