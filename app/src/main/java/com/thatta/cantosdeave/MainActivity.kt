package com.thatta.cantosdeave

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thatta.cantosdeave.databinding.ActivityMainBinding
import com.thatta.cantosdeave.model.Bird
import com.thatta.cantosdeave.apiService.BirdsAPIService
import com.thatta.cantosdeave.viewModel.BirdsAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mAdapter: BirdsAdapter = BirdsAdapter()
    private lateinit var mRecyclerView: RecyclerView
    private val birds = mutableListOf<Bird>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getRetrofit()
        setUpRecyclerView()
        Log.d("api", "se ejecuta app")

        binding.btnSearch.setOnClickListener {
            searchByName(binding.tiCountry.text.toString())
        }

    }


    private fun setUpRecyclerView() {
        mRecyclerView = binding.rvBirds
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mAdapter.birdsRecyclerAdapter(birds, this)
        binding.rvBirds.adapter = mAdapter

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun searchByName(query: String) {
        Log.d("api", "antes de corrutina: $query")
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit()
                .create(BirdsAPIService::class.java)
                .getBirdsNames("cnt:$query")

            val birdsInfo = call.body()
            Log.d("api", "en la corrutina: $birdsInfo")
            runOnUiThread {
                if (call.isSuccessful) {
                    val bird = birdsInfo?.birds ?: emptyList()
                    birds.clear()
                    birds.addAll(bird)
                    Log.d("api", "hilo ppal, trae info")
                    mAdapter.notifyDataSetChanged()

                } else {
                    showError()
                }
            }
        }
    }

    private fun showError() {
        Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://xeno-canto.org/api/2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}