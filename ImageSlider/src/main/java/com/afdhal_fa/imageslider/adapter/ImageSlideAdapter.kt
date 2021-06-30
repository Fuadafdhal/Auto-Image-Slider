package com.afdhal_fa.imageslider.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    private var titleAlignment: Int = 0

    private var titleColor: Int = -0x10000


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

    fun setTitleAlignment(value: Int) {
        titleAlignment = value
    }

    fun setTitleColor(value: Int) {
        titleColor = value
    }


    inner class VHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemImageSliderBinding.bind(itemView)

        fun onBind(model: SlideUIModel) {
            binding.imageSlide.load(model.imageUrl) {
                placeholder(placeholder)
                error(errorImage)
            }
            if (withTitle) {
                binding.titleContainer.visibility = View.VISIBLE
                binding.textTitle.text = model.title
                binding.imageBackgroundTitle.load(background)
                binding.textTitle.gravity = titleAlignment
                binding.textTitle.setTextColor(titleColor)
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
}