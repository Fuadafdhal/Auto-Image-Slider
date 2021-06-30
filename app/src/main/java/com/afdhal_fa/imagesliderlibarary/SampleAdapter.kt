package com.afdhal_fa.imagesliderlibarary

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.afdhal_fa.imagesliderlibarary.databinding.ItemSampleImageSliderBinding

/**
 * Created by Muh Fuad Afdhal on 30/06/2021
 * Email: fuad.afdal.fa@gmail.com
 */

class SampleAdapter : RecyclerView.Adapter<SampleAdapter.VHolder>() {
    private var items: MutableList<BannerUIModel> = mutableListOf()

    var onItemClick: ((BannerUIModel) -> Unit)? = null

    fun setItem(items: List<BannerUIModel>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }


    inner class VHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemSampleImageSliderBinding.bind(itemView)

        fun onBind(model: BannerUIModel) {
            binding.imageSlide.load(model.imageUrl)
            binding.textTitle.text = model.title

            binding.root.setOnClickListener {
                onItemClick?.invoke(model)
            }
        }
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: VHolder, position: Int) {
        holder.onBind(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = VHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_sample_image_slider, parent, false)
    )
}