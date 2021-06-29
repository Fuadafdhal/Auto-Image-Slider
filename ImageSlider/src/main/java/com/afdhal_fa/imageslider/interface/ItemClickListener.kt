package com.afdhal_fa.imageslider.`interface`

import com.afdhal_fa.imageslider.model.SlideUIModel

/**
 * Created by Muh Fuad Afdhal on 30/06/2021
 * Email: fuad.afdal.fa@gmail.com
 */

interface ItemClickListener {
    /**
     * * Click listener item function.
     * @param model click item model
     * @param position click item position
     */
    fun onItemClick(model: SlideUIModel, position: Int)
}