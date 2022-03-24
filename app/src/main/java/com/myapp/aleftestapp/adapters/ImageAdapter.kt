package com.myapp.aleftestapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.myapp.aleftestapp.R
import com.myapp.aleftestapp.databinding.ImageItemBinding

class ImageAdapter(
    private val images: List<String>,
    private val openImage: (String) -> Unit
): RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    inner class ImageViewHolder(val binding: ImageItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
            ImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val image = images[position]
        Glide.with(holder.binding.root)
            .load(image)
            .placeholder(R.drawable.ic_image)
            .error(R.drawable.ic_error)
            .into(holder.binding.image)

        holder.binding.root.setOnClickListener {
            openImage(image)
        }
    }

    override fun getItemCount(): Int = images.size
}
