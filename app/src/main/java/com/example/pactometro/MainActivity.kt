package com.example.pactometro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private var partidos_recycler: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        partidos_recycler = findViewById<RecyclerView>(R.id.partidos_recycler)
        loadPartidos()
    }
    private fun loadPartidos() {
        //initiate the service
        val destinationService  = ServiceBuilder.buildService(PartidosService::class.java)
        val requestCall =destinationService.getResultados()
        //make network call asynchronously
        requestCall.enqueue(object : Callback<List<Partido>>{
            override fun onResponse(call: Call<List<Partido>>, response: Response<List<Partido>>) {
                Log.d("Response", "onResponse: ${response.body()}")
                if (response.isSuccessful){
                    val countryList  = response.body()!!
                    Log.d("Response", "partidos list size : ${countryList.size}")
                    partidos_recycler?.apply {
                        setHasFixedSize(true)
                        // layoutManager = LinearLayoutManager(this@MainActivity)
                        layoutManager = GridLayoutManager(this@MainActivity,2)
                        adapter = PartidosAdapter(countryList)
                    }
                }else{
                    Toast.makeText(this@MainActivity, "Something went wrong ${response.message()}", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<List<Partido>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Something went wrong $t", Toast.LENGTH_SHORT).show()
            }
        })
    }
}