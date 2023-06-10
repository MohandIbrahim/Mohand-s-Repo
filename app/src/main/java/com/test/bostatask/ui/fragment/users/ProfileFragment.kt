package com.test.bostatask.ui.fragment.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.test.bostatask.common.extensions.showToast
import com.test.bostatask.data.models.navigation.UserArgs
import com.test.bostatask.databinding.FragmentProfileBinding
import com.test.bostatask.ui.adapter.AlbumsAdapter
import com.test.bostatask.ui.adapter.UsersAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment :Fragment() {

    private val args by navArgs<ProfileFragmentArgs>()
    private lateinit var binding : FragmentProfileBinding
    private val  viewModel: SharedViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.profileTitle.text = args.userArgs.name
        binding.profileAddress.text= args.userArgs.address
        getAlbums()
    }


    private fun getAlbums(){
        viewModel.getAlbums(args.userArgs.userId).observe(viewLifecycleOwner){
            val adapter = AlbumsAdapter(it,::itemsOnClickListener )
            binding.albumsList.adapter = adapter
            adapter.notifyItemInserted(it.size - 1)
        }
    }

    private fun itemsOnClickListener(albumId:Long){
        var action = ProfileFragmentDirections.actionProfileFragmentToPhotosFragment(albumId)
        findNavController().navigate(action)
    }
}