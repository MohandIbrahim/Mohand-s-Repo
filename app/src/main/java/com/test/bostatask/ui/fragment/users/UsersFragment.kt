package com.test.bostatask.ui.fragment.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.test.bostatask.data.models.navigation.UserArgs
import com.test.bostatask.ui.adapter.UsersAdapter
import com.test.task.databinding.FragmentUsersBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UsersFragment : Fragment() {

    private lateinit var binding : FragmentUsersBinding
    private val  viewModel: SharedViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentUsersBinding.inflate(layoutInflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getUsers()
    }
    private fun getUsers(){
        viewModel.usersVisitList.observe(viewLifecycleOwner){
                    val adapter = UsersAdapter(it,::itemsOnClickListener )
                    binding.usersList.adapter = adapter
                    adapter.notifyItemInserted(it.size - 1)
        }
    }

    private fun itemsOnClickListener(userID:Long, userName:String, userAddress:String){
        var userArgs = UserArgs(userAddress,userID,userName)
        var action = UsersFragmentDirections.actionUsersFragmentToProfileFragment(userArgs)
        findNavController().navigate(action)
    }


}