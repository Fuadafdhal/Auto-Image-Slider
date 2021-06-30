package com.afdhal_fa.imagesliderlibarary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.afdhal_fa.imageslider.`interface`.ItemClickListener
import com.afdhal_fa.imageslider.model.SlideUIModel
import com.afdhal_fa.imagesliderlibarary.databinding.ActivityWithCustomAdapterBinding
import com.afdhal_fa.imagesliderlibarary.databinding.ItemImageSliderBinding

class WithCustomAdapterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWithCustomAdapterBinding

    private val mAdapter by lazy { ImageSlideAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWithCustomAdapterBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val imageList = ArrayList<String>() // Create image list
        imageList.add("https://bit.ly/37Rn50u")
        imageList.add("https://bit.ly/2BteuF2")
        imageList.add("https://bit.ly/3fLJf72")
        mAdapter.setItem(imageList)
        binding.imageSlide.setImageListWithAdapter(mAdapter, imageList.size)
        mAdapter.onItemClick = {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }
}