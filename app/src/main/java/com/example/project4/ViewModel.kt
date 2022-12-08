package com.example.project4

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import org.json.JSONObject


class ViewModel : ViewModel() {
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

    fun currentJoke(queue: RequestQueue, jokeType: String) {
        val url: String = ""
        if (jokeType.isEmpty()) {
            val url: Uri = Uri.parse("https://dad-jokes.p.rapidapi.com/random/joke/")
        } else {
            val url: Uri = Uri.parse("https://dad-jokes.p.rapidapi.com/joke/type/$jokeType")
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
