package com.thatta.cantosdeave.userInterface

import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.thatta.cantosdeave.R
import com.thatta.cantosdeave.databinding.ActivityShowItemBinding

class ShowItem : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityShowItemBinding
    var mediaPlayer =  MediaPlayer()
    private lateinit var map: GoogleMap
    private var gpsLat: Double = 0.0
    private var gpsLng: Double = 0.0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getStringExtra("id").toString()
        val name = intent.getStringExtra("name").toString()
        val audioUrl = intent.getStringExtra("audioUrl").toString()
        gpsLat = intent.getDoubleExtra("gpsLat", 0.0)
        gpsLng = intent.getDoubleExtra("gpsLng", 0.0)

        Log.d("play", "init activity: $audioUrl")


        setUI(id, name)
        playSound(audioUrl)
        createMapFragment()


    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        createMarker(gpsLat, gpsLng)

    }

    private fun createMarker(gpsLat: Double, gpsLng: Double) {
        val recordingPlace = LatLng(gpsLat, gpsLng)
        map.addMarker(MarkerOptions()
            .position(recordingPlace)
            .title("Lugar de grabación del sonido"))
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(recordingPlace, 11f)
            , 4000
            , null)
    }

    private fun createMapFragment() {
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentMap) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun setUI(id: String, name: String) {
        binding.tvBirdIdItem.text = id
        binding.tvBirdNameItem.text = name
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()
            mediaPlayer.reset()
            mediaPlayer.release()
            Log.d("play", "End mediaPlayer")

        }
    }

    private fun playSound(audioUrl: String) {

        Log.d("play", "media player: $mediaPlayer")
        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()
            mediaPlayer.reset()
            Log.d("play", "STOP player")
        } else {

            mediaPlayer.setAudioAttributes(
                AudioAttributes.Builder()
                    .setLegacyStreamType(AudioManager.STREAM_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build()
            )

            mediaPlayer.setDataSource(audioUrl)
            mediaPlayer.prepareAsync()
            mediaPlayer.setOnPreparedListener(MediaPlayer.OnPreparedListener {
                Log.d("play", "prepared async")
                mediaPlayer.start()
            })
        }
    }
}