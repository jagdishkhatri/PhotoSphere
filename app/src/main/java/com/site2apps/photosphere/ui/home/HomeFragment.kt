package com.site2apps.photosphere.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.site2apps.photosphere.R
import com.site2apps.photosphere.data.model.ImageResult
import com.site2apps.photosphere.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private lateinit var imageAdapter: ImageAdapter
    private val viewModel: HomeViewModel by viewModel()
    private var currentPage = 1
    private val perPage = 20

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)

        val apiKey = "47375530-646c5d3591a78541380036fbe"  // Replace with your actual API key
        val query = "yellow flowers"  // Example query (could be dynamic)
        viewModel.fetchImages(apiKey, query, currentPage, perPage)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
        }

        viewModel.imageList.observe(viewLifecycleOwner) { images ->
            imageAdapter = ImageAdapter(images, object : ImageAdapter.OnItemClickListener {
                override fun onItemClick(image: ImageResult) {
                    val bundle = Bundle().apply {
                        putParcelable("image", image)
                    }
                    findNavController().navigate(R.id.action_homeFragment_to_imageDetailFragment, bundle)
                }
            })
            binding.recyclerView.adapter = imageAdapter
        }

        // Handle pagination
        val layoutManager = binding.recyclerView.layoutManager as LinearLayoutManager
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val pastVisibleItems = layoutManager.findFirstVisibleItemPosition()

                if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                    currentPage++
                    viewModel.fetchImages(apiKey, query, currentPage, perPage)
                }
            }
        })

        return binding.root
    }
}
