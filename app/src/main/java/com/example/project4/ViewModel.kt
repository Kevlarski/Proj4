package com.example.project4


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException


class ViewModel : ViewModel() {
    private val apiKey: String = "ce9834b9d2msh35b73155c38ea24p17cb8ajsn01dd3df894a4"
    private val host: String = "dad-jokes.p.rapidapi.com"
    private var punchline: MutableLiveData<String> = MutableLiveData()
    private var setup: MutableLiveData<String> = MutableLiveData()
    private var type: MutableLiveData<String> = MutableLiveData()
    private var image: MutableLiveData<String> = MutableLiveData()
    private val client = OkHttpClient()

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

    fun currentJoke(jokeType: String) {

        val urlBuilder: HttpUrl.Builder?
        if (jokeType == "none") {
            urlBuilder =
                "https://dad-jokes.p.rapidapi.com/random/joke".toHttpUrlOrNull()!!.newBuilder()
        } else {
            urlBuilder = "https://dad-jokes.p.rapidapi.com/joke/search?term=$jokeType".toHttpUrlOrNull()!!
                .newBuilder()
        }


        val url: String = urlBuilder.build().toString()

        val request = Request.Builder()
            .url(url)
            .get()
            .addHeader("X-RapidAPI-Key", apiKey)
            .addHeader("X-RapidAPI-Host", host)
            .build()

            client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                call.cancel()
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val responseData = response.body!!.string()
                    try {
                        var rand  = (Math.random()*10).toInt()
                        var json: JSONArray =
                            JSONObject(responseData).getJSONArray("body")
                        setup.postValue(json.getJSONObject(rand).getString("setup"))
                        punchline.postValue(json.getJSONObject(rand).getString("punchline"))
                    }catch( e: JSONException){
                        setup.postValue("no jokes")
                    }
                }
            }
        })
    }
}
