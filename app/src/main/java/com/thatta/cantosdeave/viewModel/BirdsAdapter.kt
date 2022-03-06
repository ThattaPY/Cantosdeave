package com.thatta.cantosdeave.viewModel

import android.content.Context
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thatta.cantosdeave.R
import com.thatta.cantosdeave.databinding.ItemBirdBinding
import com.thatta.cantosdeave.model.Bird

class BirdsAdapter():

    RecyclerView.Adapter<BirdsAdapter.ViewHolder>() {

    private var birds: MutableList<Bird> = ArrayList()
    private lateinit var context: Context

    fun birdsRecyclerAdapter(birds: MutableList<Bird>, context: Context) {
        this.birds = birds
        this.context = context
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val binding = ItemBirdBinding.bind(view)

        fun playBird(audioUrl: String, mediaPlayer: MediaPlayer) {

            Log.d("play", "media player bind view: $mediaPlayer")

            binding.btnPlay.setOnClickListener {



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
        fun bind(bird: Bird) {
            binding.tvBirdName.text = bird.birdName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_bird, parent, false))

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = birds[position]
        holder.bind(item)
        var mediaPlayer =  MediaPlayer()
        holder.playBird(item.soundUrl, mediaPlayer)

    }

    override fun getItemCount(): Int = birds.size

}