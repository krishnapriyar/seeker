package com.seeker.seeker.ui.job

import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.seeker.seeker.R
import com.seeker.seeker.databinding.FragmentJobDetailBinding
import com.seeker.seeker.databinding.FragmentLoginBinding
import com.seeker.seeker.model.ui.Job
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JobDetailFragment : Fragment() {

    companion object {
        fun newInstance() = JobDetailFragment()
    }

    private lateinit var viewModel: JobViewModel

    private var _binding: FragmentJobDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = ViewModelProvider(this)[JobViewModel::class.java]

        _binding = FragmentJobDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val job = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getSerializable("job", Job::class.java)
        }else{
            arguments?.getSerializable("job")
        } as Job?

        binding.textJobTitle.text = job?.jobTitle
        binding.textCompanyName.text = job?.companyName
        binding.textLocation.text = job?.location
        binding.textJobDescription.text = job?.jobDescription

        if (job?.applied ==  true){
            binding.buttonApply.isEnabled = false
            binding.buttonApply.text = getString(R.string.applied)
        }else{
            binding.buttonApply.isEnabled = true
            binding.buttonApply.text = getString(R.string.apply)
        }


    }

}