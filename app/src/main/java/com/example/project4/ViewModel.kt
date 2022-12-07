package com.example.project4

import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import okhttp3.OkHttpClient
import java.net.URL

class ViewModel {
    private val apiKey: String = "ce9834b9d2msh35b73155c38ea24p17cb8ajsn01dd3df894a4"
    private val host: String = "dad-jokes.p.rapidapi.com"
    private var punchline: MutableLiveData<String> = MutableLiveData()
    private var setup: MutableLiveData<String> = MutableLiveData()
    private var type: MutableLiveData<String> = MutableLiveData()
    private var image: MutableLiveData<String> = MutableLiveData()

    fun getSetup(): MutableLiveData<String> {
        return setup
    }
    fun getPunchline(): MutableLiveData<String> {
        return punchline
    }
    fun getType(): MutableLiveData<String> {
        return type
    }
    fun getImage(): MutableLiveData<String> {
        return image
    }

//    val client = OkHttpClient()
//    val request = okhttp3.Request.Builder()
//        .url(currentJoke())
//        .get()
//        .addHeader("X-RapidAPI-Key", "ce9834b9d2msh35b73155c38ea24p17cb8ajsn01dd3df894a4")
//        .addHeader("X-RapidAPI-Host", "dad-jokes.p.rapidapi.com")
//        .build()
//
//    val response = client.newCall(request).execute()

        fun currentJoke(joke: String) {
            val url: String = ""
            if(joke.isEmpty()){
                val url= "https://dad-jokes.p.rapidapi.com/random/joke/"
            }else{
                val url= "https://dad-jokes.p.rapidapi.com/joke/type/$joke"
            }
            val client = OkHttpClient()
            val request = okhttp3.Request.Builder()
                .url(url)
                .get()
                .addHeader("X-RapidAPI-Key", "ce9834b9d2msh35b73155c38ea24p17cb8ajsn01dd3df894a4")
                .addHeader("X-RapidAPI-Host", "dad-jokes.p.rapidapi.com")
                .build()

            val response = client.newCall(request).execute()
        }
    }
