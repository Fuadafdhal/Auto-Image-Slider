package com.afdhal_fa.imageslider

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.afdhal_fa.imageslider.`interface`.ItemClickListener
import com.afdhal_fa.imageslider.adapter.ImageSlideAdapter
import com.afdhal_fa.imageslider.model.SlideUIModel
import java.util.*
import kotlin.concurrent.timerTask

/**
 * Created by Muh Fuad Afdhal on 30/06/2021
 * Email: fuad.afdal.fa@gmail.com
 */

class ImageSlider @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet,
    defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    private var viewPager: ViewPager2? = null
    private var pagerDots: LinearLayout? = null

    private val mAdapter by lazy { ImageSlideAdapter() }

    private var dots: Array<ImageView?>? = null

    private var currentPage = 0
    private var imageCount = 0

    private var cornerRadius: Int = 0
    private var period: Long = 0
    private var delay: Long = 0
    private var autoCycle = false

    private var selectedDot = 0
    private var unselectedDot = 0
    private var errorImage = 0
    private var placeholder = 0
    private var titleBackground = 0
    private var isTitle = true
    private var textAlign = "LEFT"
    private var indicatorAlign = "CENTER"
    private var swipeTimer = Timer()

    init {
        LayoutInflater.from(getContext()).inflate(R.layout.image_slider, this, true)
        viewPager = findViewById(R.id.view_pager)
        pagerDots = findViewById(R.id.pager_dots)

        val typedArray = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.ImageSlider,
            defStyleAttr,
            defStyleAttr
        )

        cornerRadius = typedArray.getInt(R.styleable.ImageSlider_iss_corner_radius, 1)
        period = typedArray.getInt(R.styleable.ImageSlider_iss_period, 1000).toLong()
        delay = typedArray.getInt(R.styleable.ImageSlider_iss_delay, 1000).toLong()
        autoCycle = typedArray.getBoolean(R.styleable.ImageSlider_iss_auto_cycle, false)
        placeholder = typedArray.getResourceId(R.styleable.ImageSlider_iss_placeholder, R.drawable.ic_image_placeholder)
        errorImage = typedArray.getResourceId(R.styleable.ImageSlider_iss_error_image, R.drawable.ic_image_broken)
        selectedDot = typedArray.getResourceId(R.styleable.ImageSlider_iss_selected_dot, R.drawable.indicator_active)
        unselectedDot = typedArray.getResourceId(R.styleable.ImageSlider_iss_unselected_dot, R.drawable.indicator_inactive)
        titleBackground = typedArray.getResourceId(R.styleable.ImageSlider_iss_title_background, R.drawable.gradient)
        isTitle = typedArray.getBoolean(R.styleable.ImageSlider_iss_title, true)

        typedArray.getString(R.styleable.ImageSlider_iss_text_align)?.let {
            textAlign = it
        }

        typedArray.getString(R.styleable.ImageSlider_iss_indicator_align)?.let {
            indicatorAlign = it
        }
    }


    /**
     * Set image list to adapter.
     *
     * @param  imageList  the image list by user
     */
    fun setImageList(imageList: List<SlideUIModel>) {
        imageCount = imageList.size
        mAdapter.setItem(imageList)
        mAdapter.setErrorImage(errorImage)
        mAdapter.setPlaceholderImage(placeholder)
        mAdapter.setBackgroundImage(titleBackground)
        mAdapter.setIsTitle(isTitle)
        viewPager?.adapter = mAdapter
        if (imageList.isNotEmpty()) {
            setupDots(imageList.size)
            setCurrentIndicator(currentPage)
            if (autoCycle) {
                stopSliding()
                startSliding()
            }
        }
    }

    private fun setImageListAdapter(adapter: RecyclerView.Adapter<*>?, size: Int = 0) {
        imageCount = size
        viewPager?.adapter = adapter
        if (size != 0) {
            setupDots(size)
            setCurrentIndicator(currentPage)
            if (autoCycle) {
                stopSliding()
                startSliding()
            }
        }
    }

    private fun setupDots(size: Int) {
        println(indicatorAlign)
        pagerDots?.gravity = getGravityFromAlign(indicatorAlign)
        pagerDots?.removeAllViews()
        dots = arrayOfNulls(size)
        val params: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        params.setMargins(8, 0, 8, 0)

        for (i in 0 until size) {
            dots!![i] = ImageView(context)
            pagerDots?.addView(dots!![i], params)
        }
        viewPager?.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                currentPage = position
                setCurrentIndicator(currentPage)
            }
        })
    }

    private fun setCurrentIndicator(index: Int) {
        val childCount = pagerDots!!.childCount

        for (i in 0 until childCount) {
            val imageView = pagerDots!![i] as ImageView
            imageView.setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.indicator_selector
                )
            )
            imageView.isSelected = i == index
        }
    }

    /**
     * Start image sliding.
     *
     * @param  changeablePeriod  optional period value
     */
    fun startSliding(changeablePeriod: Long = period) {
        stopSliding()
        scheduleTimer(changeablePeriod)
    }

    /**
     * Stop image sliding.
     *
     */
    fun stopSliding() {
        swipeTimer.cancel()
        swipeTimer.purge()
    }


    private fun scheduleTimer(period: Long) {
        val handler = Handler(Looper.getMainLooper())
        val update = Runnable {
            if (currentPage == imageCount) {
                currentPage = 0
            }
            viewPager?.setCurrentItem(currentPage++, true)
        }
        swipeTimer = Timer()
        swipeTimer.schedule(timerTask {
            handler.post(update)
        }, delay, period)
    }

    /**
     * Set item click listener for listen to image click
     *
     * @param  itemClickListener  interface callback
     */
    var onItemClickListener = mAdapter.onItemClick


    /**
     * Set item click listener for listen to image click
     *
     * @param  itemClickListener  interface callback
     */
    fun setItemClickListener(itemClickListener: ItemClickListener) {
        mAdapter.setItemClickListener(itemClickListener)
    }


    /**
     * Get layout gravity value from indicatorAlign variable
     *
     * @param  textAlign  indicator align by user
     */
    fun getGravityFromAlign(textAlign: String): Int {
        return when (textAlign) {
            "RIGHT" -> {
                Gravity.END
            }
            "LEFT" -> {
                Gravity.START
            }
            else -> {
                Gravity.CENTER
            }
        }
    }


}