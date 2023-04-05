package com.shem.adv160420033week4.util

import android.widget.ImageView
import android.widget.ProgressBar
import com.shem.adv160420033week4.R
import com.squareup.picasso.Picasso

fun ImageView.loadImage(url: String?, progressBar: ProgressBar){
    Picasso.get()
        .load(url)
        .resize(400, 400)
        .centerCrop()
        .error(R.drawable.baseline_error_24)
        .into(this)
}