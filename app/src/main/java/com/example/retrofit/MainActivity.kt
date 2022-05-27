package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.retrofit.remote.PokemonEntry
import com.example.retrofit.remote.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = RetrofitBuilder.create().getPokemonById("35")

        retrofit.enqueue(object : Callback<PokemonEntry>{
            override fun onResponse(call: Call<PokemonEntry>, response: Response<PokemonEntry>) {
                val resBody = response.body()
                if (resBody != null){
                    Log.d("retrofitresponse", "res: ${resBody}")
                    Log.d("retrofitresponse", "res: ${resBody.name}")
                    for (stat in resBody.stats) {
                        Log.d("retrofitresponse", "stats: ${stat.stat.stat_value}: ${stat.base_stat}")
                    }
                    Log.d("retrofitresponse", "type: ${resBody.types[0].type.name} ")
                    Log.d("retrofitresponse", "type: ${resBody.sprites.front_default} ")
                }
            }

            override fun onFailure(call: Call<PokemonEntry>, t: Throwable) {
                Log.e("retrofitresponse", "error: ${t.message}")
            }

        }
        )
    }
}