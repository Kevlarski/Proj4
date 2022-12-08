package com.example.project4

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
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

        val url = ""
        if (jokeType.isEmpty()) {
            val url = "https://dad-jokes.p.rapidapi.com/random/joke/"
        } else {
            val url = "https://dad-jokes.p.rapidapi.com/joke/type/"+jokeType
        }
        println(url)
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                // create JSONObject
                val obj = JSONObject(response)
                val body = obj.getJSONArray("body").getJSONObject(0)
                setup.setValue(body.getString("setup"))
                punchline.setValue(body.getString("punchline"))
                val imageUrl = "https://dad-jokes.p.rapidapi.com/random/joke/png"
                image.setValue(imageUrl)
            },
            Response.ErrorListener { "oops" })

// Add the request to the RequestQueue.
        queue.add(stringRequest)

    }
}
