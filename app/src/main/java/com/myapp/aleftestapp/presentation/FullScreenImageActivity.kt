package com.myapp.aleftestapp.presentation

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.myapp.aleftestapp.databinding.ActivityFullScreenImageBinding

class FullScreenImageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFullScreenImageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFullScreenImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val image = intent.getStringExtra("image_uri")

        Glide.with(this)
            .load(image)
            .listener(ErrorListener(binding))
            .into(binding.fullScreenImage)
    }
}

private class ErrorListener(
    val binding: ActivityFullScreenImageBinding
): RequestListener<Drawable> {
    override fun onLoadFailed(
        e: GlideException?,
        model: Any?,
        target: Target<Drawable>?,
        isFirstResource: Boolean
    ): Boolean {
        binding.errorText.text = e?.localizedMessage
        binding.errorText.visibility = View.VISIBLE
        return false
    }

    override fun onResourceReady(
        resource: Drawable?,
        model: Any?,
        target: Target<Drawable>?,
        dataSource: DataSource?,
        isFirstResource: Boolean
    ): Boolean {
        return false
    }
}