package com.example.project5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers

class MainActivity : AppCompatActivity() {
    var petImageURL = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getDogImageURL()

        val button = findViewById<Button>(R.id.pokeButton)
        val button2 = findViewById<Button>(R.id.pokeButton2)
        val button3 = findViewById<Button>(R.id.pokeButton3)
        val image1= findViewById<ImageView>(R.id.pokeImage)
        val image2= findViewById<ImageView>(R.id.pokeImage2)
        val image3= findViewById<ImageView>(R.id.pokeImage3)
        getNextImage(button, image1)
        getNextImage(button2, image2)
        getNextImage(button3, image3)
    }


    private fun getDogImageURL() {
        val client = AsyncHttpClient()
        client["https://dog.ceo/api/breeds/image/random", object : JsonHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Headers,
                json: JsonHttpResponseHandler.JSON
            ) {
                Log.d("Dog", "response successful$json")
                petImageURL = json.jsonObject.getString("message")
            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?
            ) {
                Log.d("Dog Error", errorResponse)
            }
        }]
    }
        private fun getNextImage(button: Button, imageView: ImageView) {
            button.setOnClickListener {
                getDogImageURL()

                Glide.with(this)
                    . load(petImageURL)
                    .fitCenter()
                    .into(imageView)
            }
        }
    }
