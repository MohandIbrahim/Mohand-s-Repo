package com.test.bostatask.ui.fragment.users

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.test.bostatask.common.extensions.openPhotoDialog
import com.test.bostatask.common.extensions.showToast
import com.test.bostatask.data.models.PhotosModel
import com.test.bostatask.databinding.FragmentPhotosBinding
import com.test.bostatask.ui.adapter.PhotosAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlin.collections.ArrayList

@AndroidEntryPoint
class PhotosFragment : Fragment() {

    private val args by navArgs<PhotosFragmentArgs>()
    private lateinit var binding : FragmentPhotosBinding
    private val  viewModel: SharedViewModel by viewModels()
    private lateinit var photosList:ArrayList<PhotosModel.PhotosModelItem>
    private lateinit var globalAdapter: PhotosAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentPhotosBinding.inflate(layoutInflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getPhotos()
        binding.searchField.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                filterPhotos(s.toString())
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(query1: CharSequence, start: Int, before: Int, count: Int) {

            }
        })
    }
    private fun getPhotos(){
        viewModel.getPhotos(args.albumId).observe(viewLifecycleOwner){
            globalAdapter = PhotosAdapter(it,::itemsOnClickListener )
            photosList = it
            binding.photosRecyclerList.adapter = globalAdapter
            binding.photosRecyclerList.layoutManager = GridLayoutManager(requireContext(), 3)
            globalAdapter.notifyItemInserted(it.size - 1)
        }
    }
    private fun itemsOnClickListener(photoTitle:String, photoUrl:String){
        openPhotoDialog(photoTitle,photoUrl)
    }
    fun filterPhotos(text:String){
        val filteredList:ArrayList<PhotosModel.PhotosModelItem> = ArrayList()
        for(value in photosList){
            if (value.title.lowercase().contains(text.lowercase())){
                filteredList.add(value)
            }
        }
        globalAdapter.filterList(filteredList)
    }
}