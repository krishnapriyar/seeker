package com.seeker.seeker.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.seeker.seeker.R
import com.seeker.seeker.databinding.FragmentHomeBinding
import com.seeker.seeker.model.ui.Job
import com.seeker.seeker.ui.adapter.JobListViewType
import com.seeker.seeker.ui.adapter.JobsAdapter
import com.seeker.seeker.ui.adapter.OnItemClickListener
import com.seeker.seeker.ui.job.JobDetailFragment

class HomeFragment : Fragment(), OnItemClickListener {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        initObservers()
        homeViewModel.getJobList()
        return binding.root
    }

    private fun initObservers(){
        homeViewModel.jobsObserver.observe(viewLifecycleOwner){
            val jobsRecyclerView = binding.recylerviewJobs
            jobsRecyclerView.layoutManager = LinearLayoutManager(context)
            jobsRecyclerView.adapter = JobsAdapter(it, JobListViewType.HOME, this)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onJobItemClicked(job: Job){
        val bundle = bundleOf("job" to job)
        findNavController().navigate(R.id.action_navigation_home_to_jobDetailFragment, bundle)
    }
}