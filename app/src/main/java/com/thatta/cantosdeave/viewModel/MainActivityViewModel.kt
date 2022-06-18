package com.thatta.cantosdeave.viewModel

import android.annotation.SuppressLint
import android.app.Application
import androidx.annotation.NonNull
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thatta.cantosdeave.model.Bird
import com.thatta.cantosdeave.model.Birds
import com.thatta.cantosdeave.model.BirdsRepository


class MainActivityViewModel: ViewModel() {

    private lateinit var birdsRepository: BirdsRepository



    @SuppressLint("NotConstructor")
    fun mainActivityViewModel(@NonNull application: Application) {
        birdsRepository = BirdsRepository()


    }

    suspend fun getAllBirds(query: String): MutableLiveData<Birds> {
        mainActivityViewModel(application = Application())
        return birdsRepository.getMutableLiveData(query)
    }


}