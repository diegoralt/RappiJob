package com.diego.rappijob.util

import android.content.Context
import android.net.ConnectivityManager
import android.view.View
import android.widget.ImageView
import com.diego.rappijob.R
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import java.util.Locale

object Utils {
    private const val URL_IMAGE = "https://image.tmdb.org/t/p/original"

    fun loadImage(context: Context, urlImage: String?, imageView: ImageView) {
        Picasso.with(context).load("$URL_IMAGE$urlImage").placeholder(R.drawable.vd_ic_poster_error)
            .error(R.drawable.vd_ic_poster_error).into(imageView)
    }

    fun getLanguage(): String =
        Locale.getDefault().language

    fun isNetworkOnline(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo?.isConnectedOrConnecting == true
    }

    fun makeSnackbar(view: View, resId: Int, lenght:Int = Snackbar.LENGTH_INDEFINITE): Snackbar =
        Snackbar.make(view, resId, lenght)
}