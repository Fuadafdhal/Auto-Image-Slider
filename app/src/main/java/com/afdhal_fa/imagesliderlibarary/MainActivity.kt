package com.afdhal_fa.imagesliderlibarary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.afdhal_fa.imageslider.`interface`.ItemClickListener
import com.afdhal_fa.imageslider.model.SlideUIModel
import com.afdhal_fa.imagesliderlibarary.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val imageList = ArrayList<SlideUIModel>() // Create image list
        imageList.add(SlideUIModel("https://bit.ly/37Rn50u", "Baby Owl"))
        imageList.add(SlideUIModel("https://bit.ly/2BteuF2", "Elephants and tigers may become extinct."))
        imageList.add(SlideUIModel("https://bit.ly/3fLJf72", "The population of elephants is decreasing in the world."))

        binding.imageSlide.setImageList(imageList)
        binding.imageSlide.setItemClickListener(object : ItemClickListener {
            override fun onItemClick(model: SlideUIModel, position: Int) {
                Toast.makeText(this@MainActivity, "${model.title}", Toast.LENGTH_SHORT).show()
            }
        })

        binding.buttonWithCustomAdapter.setOnClickListener {
            startActivity(Intent(this, WithCustomAdapterActivity::class.java))
        }
    }
}