package com.site2apps.photosphere.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.site2apps.photosphere.data.model.ImageResult
import com.site2apps.photosphere.databinding.FragmentImageDetailBinding

class ImageDetailFragment : Fragment() {

    private lateinit var binding: FragmentImageDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentImageDetailBinding.inflate(inflater, container, false)

        val imageResult = arguments?.getParcelable<ImageResult>("image")

        imageResult?.let {

            Glide.with(binding.root.context).load(imageResult.largeImageURL).into(binding.imageView)
            binding.imageSize.text = "Size: ${imageResult.imageSize} bytes"
            binding.imageType.text = "Type: ${imageResult.type}"
            binding.imageTags.text = "Tags: ${imageResult.tags}"

            // Set user data
            binding.userName.text = imageResult.user
            binding.views.text = "Views: ${imageResult.views}"
            binding.likes.text = "Likes: ${imageResult.likes}"
            binding.comments.text = "Comments: ${imageResult.comments}"
            binding.favorites.text = "Favorites: ${imageResult.favorites}"
            binding.downloads.text = "Downloads: ${imageResult.downloads}"
        }

        return binding.root
    }
}
