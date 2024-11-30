package com.site2apps.photosphere.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.site2apps.photosphere.data.model.ImageResult
import com.site2apps.photosphere.databinding.ItemImageBinding

class ImageAdapter(private val images: List<ImageResult>, private val listener: OnItemClickListener) :
    RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    inner class ImageViewHolder(private val binding: ItemImageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(image: ImageResult) {
            binding.apply {
                // Bind data to the views
                userName.text = image.user
                tags.text = image.tags
                Glide.with(imageThumbnail.context)
                    .load(image.previewURL)
                    .into(imageThumbnail)

                // Click listener for item
                root.setOnClickListener {
                    listener.onItemClick(image)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(images[position])
    }

    override fun getItemCount(): Int = images.size

    // Interface for item click handling
    interface OnItemClickListener {
        fun onItemClick(image: ImageResult)
    }
}
