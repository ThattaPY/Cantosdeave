package com.thatta.cantosdeave.viewModel


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.thatta.cantosdeave.R
import com.thatta.cantosdeave.databinding.ItemBirdBinding
import com.thatta.cantosdeave.model.Bird
import com.thatta.cantosdeave.userInterface.ShowItem

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

        fun clickBird(id: String, name: String, audioUrl: String, gpsLat: String?,
                      gpsLng: String?, holder: ViewHolder) {

            binding.btnActivity.setOnClickListener {

                val activity = holder.itemView.context as Activity
                val intent = Intent(activity, ShowItem::class.java)
                intent.putExtra("id", id)
                intent.putExtra("name", name)
                intent.putExtra("audioUrl", audioUrl)
                intent.putExtra("gpsLat", gpsLat?.toDouble())
                intent.putExtra("gpsLng", gpsLng?.toDouble())
                startActivity(activity, intent, null)

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
        holder.clickBird(item.id, item.birdName, item.soundUrl, item.gpsLat, item.gpsLng, holder)

    }

    override fun getItemCount(): Int = birds.size

}