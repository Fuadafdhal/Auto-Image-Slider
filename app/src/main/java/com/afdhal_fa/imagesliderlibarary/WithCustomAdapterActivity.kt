package com.afdhal_fa.imagesliderlibarary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.afdhal_fa.imageslider.model.SlideUIModel
import com.afdhal_fa.imagesliderlibarary.databinding.ActivityWithCustomAdapterBinding

class WithCustomAdapterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWithCustomAdapterBinding

    private val mAdapter by lazy { SampleAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWithCustomAdapterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imageList = ArrayList<BannerUIModel>() // Create image list
        imageList.add(BannerUIModel("https://bit.ly/37Rn50u", "Baby Owl"))
        imageList.add(BannerUIModel("https://bit.ly/2BteuF2", "Elephants and tigers may become extinct."))
        imageList.add(BannerUIModel("https://bit.ly/3fLJf72", "The population of elephants is decreasing in the world."))
        mAdapter.setItem(imageList)
        binding.imageSlide.setImageListWithAdapter(mAdapter, imageList.size)
        mAdapter.onItemClick = {
            Toast.makeText(this, it.title, Toast.LENGTH_SHORT).show()
        }
    }
}