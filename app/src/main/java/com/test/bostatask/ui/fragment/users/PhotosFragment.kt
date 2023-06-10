package com.test.bostatask.ui.fragment.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.test.bostatask.common.extensions.showToast
import com.test.bostatask.databinding.FragmentPhotosBinding
import com.test.bostatask.ui.adapter.PhotosAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PhotosFragment : Fragment() {

    private val args by navArgs<PhotosFragmentArgs>()
    private lateinit var binding : FragmentPhotosBinding
    private val  viewModel: SharedViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentPhotosBinding.inflate(layoutInflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getPhotos()
    }


    private fun getPhotos(){
        viewModel.getPhotos(args.albumId).observe(viewLifecycleOwner){
            val adapter = PhotosAdapter(it,::itemsOnClickListener )
            binding.photosRecyclerList.adapter = adapter
            binding.photosRecyclerList.layoutManager = GridLayoutManager(requireContext(), 3)
            adapter.notifyItemInserted(it.size - 1)
        }
    }

    private fun itemsOnClickListener(photoTitle:String, photoUrl:String){
        showToast("$photoTitle")
    }
}