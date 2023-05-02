package com.seeker.seeker.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.seeker.seeker.R
import com.seeker.seeker.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        profileViewModel = ViewModelProvider(this)[ProfileViewModel::class.java]
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        setViewState()
        initViewListeners()

        return binding.root
    }

    private fun initViewListeners() {
        binding.buttonLogout.setOnClickListener {
            logout()
        }
    }

    private fun logout(){
        profileViewModel.logout()
        findNavController().navigate(R.id.action_navigation_profile_to_loginFragment)
        Toast.makeText(requireContext(), R.string.logout_success, Toast.LENGTH_SHORT).show()
    }

    private fun setViewState(){
        if (profileViewModel.isLoggedIn()){
            binding.buttonLogout.visibility = View.VISIBLE
            binding.profileLayout.visibility = View.VISIBLE
        }else{
            binding.buttonLogout.visibility = View.GONE
            binding.profileLayout.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}