package com.example.retrofitapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.internal.NopCollector.emit

import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textview :  TextView = findViewById(R.id.textView)


        val retrofitService = RetrofitInstance
            .getRetrofitInstance()
            .create(AlbumService::class.java)

        val reponseLiveData : LiveData<Response<Albums>> =
        liveData {
            val response = retrofitService.getAlbums()
            emit(response)
        }

        reponseLiveData.observe(this, Observer {
            val albumsList = it.body()?.listIterator()

            if (albumsList != null) {
                while (albumsList.hasNext()) {
                    val albumItem = albumsList.next()
                   // Log.i("TAGY", albumItem.title)

                    val result = " Album Title: ${albumItem.title} \n"

                    textview.append(result)


                }
            }
        })

    }
}