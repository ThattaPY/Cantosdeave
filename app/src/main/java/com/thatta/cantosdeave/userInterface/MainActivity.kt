package com.thatta.cantosdeave.userInterface

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thatta.cantosdeave.databinding.ActivityMainBinding
import com.thatta.cantosdeave.model.Bird
import com.thatta.cantosdeave.model.Birds
import com.thatta.cantosdeave.viewModel.BirdsAdapter
import com.thatta.cantosdeave.viewModel.MainActivityViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mAdapter: BirdsAdapter = BirdsAdapter()
    private lateinit var mRecyclerView: RecyclerView
    private var birdsList: List<Bird> ? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSearch.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                getBirds()


            }


        }
    }



    private suspend fun getBirds() {
        val mainActivityViewModel: MainActivityViewModel = ViewModelProvider(this).
        get(MainActivityViewModel::class.java)




        val precall = mainActivityViewModel.getAllBirds(binding.tiCountry.text.toString())
        runOnUiThread {
        precall.observe(this@MainActivity) {
                birdsList = it.birds

            }
            setUpRecyclerView()
            mAdapter.notifyDataSetChanged()
        }
    }

    private fun setUpRecyclerView() {
        mRecyclerView = binding.rvBirds
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        birdsList?.let { mAdapter.birdsRecyclerAdapter(it, this) }
        binding.rvBirds.adapter = mAdapter


    }
}