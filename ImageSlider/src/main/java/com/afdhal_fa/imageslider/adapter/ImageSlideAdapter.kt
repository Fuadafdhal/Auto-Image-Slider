package com.afdhal_fa.imageslider.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.afdhal_fa.imageslider.R
import com.afdhal_fa.imageslider.`interface`.ItemClickListener
import com.afdhal_fa.imageslider.databinding.ItemImageSliderBinding
import com.afdhal_fa.imageslider.model.SlideUIModel

/**
 * Created by Muh Fuad Afdhal on 30/06/2021
 * Email: fuad.afdal.fa@gmail.com
 */

class ImageSlideAdapter : RecyclerView.Adapter<ImageSlideAdapter.VHolder>() {
    private var items: MutableList<SlideUIModel> = mutableListOf()

    private var itemClickListener: ItemClickListener? = null

    private var errorImage: Int = 0

    private var placeholder: Int = 0

    private var background: Int = 0

    private var withTitle: Boolean = true

    private var withBackground: Boolean = true

    private var titleAlignment: Int = 0

    private var titleColor: Int = -0x10000

    private var imageScaleType: ImageView.ScaleType? = null

    inner class VHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemImageSliderBinding.bind(itemView)

        fun onBind(model: SlideUIModel) {
            binding.imageSlide.load(model.imageUrl) {
                placeholder(placeholder)
                error(errorImage)
            }
            if (imageScaleType != null) binding.imageSlide.scaleType = imageScaleType

            if (withTitle && !model.title.isNullOrEmpty()) {
                binding.textTitle.visibility = View.VISIBLE
                binding.textTitle.text = model.title
                binding.textTitle.gravity = titleAlignment
                binding.textTitle.setTextColor(titleColor)
            } else {
                binding.textTitle.visibility = View.GONE
            }

            if (withBackground) {
                binding.titleContainer.visibility = View.VISIBLE
                binding.titleContainer.setBackgroundResource(background)
            } else {
                binding.titleContainer.visibility = View.GONE
            }

            binding.root.setOnClickListener {
                itemClickListener?.onItemClick(model, adapterPosition)
            }
        }
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: VHolder, position: Int) {
        holder.onBind(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = VHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_image_slider, parent, false)
    )

    /**
     * Set item click listener
     *
     * @param  itemClickListener  callback by user
     */
    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    fun setItem(items: List<SlideUIModel>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun setErrorImage(image: Int) {
        errorImage = image
    }

    fun setPlaceholderImage(image: Int) {
        placeholder = image
    }

    fun setBackgroundImage(image: Int) {
        background = image
    }

    fun setWithTitle(it: Boolean) {
        withTitle = it
    }

    fun setWithBackground(it: Boolean) {
        withBackground = it
    }

    fun setTitleAlignment(value: Int) {
        titleAlignment = value
    }

    fun setTitleColor(value: Int) {
        titleColor = value
    }

    fun setImageScaleType(value: ImageView.ScaleType) {
        imageScaleType = value
    }
}