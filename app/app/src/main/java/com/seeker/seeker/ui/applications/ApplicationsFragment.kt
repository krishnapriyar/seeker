package com.seeker.seeker.ui.applications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.seeker.seeker.databinding.FragmentApplicationsBinding
import com.seeker.seeker.model.ui.Job
import com.seeker.seeker.ui.adapter.JobListViewType
import com.seeker.seeker.ui.adapter.JobsAdapter
import com.seeker.seeker.ui.adapter.OnItemClickListener

class ApplicationsFragment : Fragment(), OnItemClickListener {

    private var _binding: FragmentApplicationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var applicationsViewModel: ApplicationsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        applicationsViewModel =
            ViewModelProvider(this)[ApplicationsViewModel::class.java]

        _binding = FragmentApplicationsBinding.inflate(inflater, container, false)
        initObservers()
        applicationsViewModel.getAppliedJobsList()
        return binding.root
    }

    private fun initObservers(){
        applicationsViewModel.applicationsObserver.observe(viewLifecycleOwner){
            val jobsRecyclerView = binding.recylerviewApplications
            jobsRecyclerView.layoutManager = LinearLayoutManager(context)
            jobsRecyclerView.adapter = JobsAdapter(it, JobListViewType.APPLIED, this)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onJobItemClicked(job: Job) {
        //findNavController().navigate()
    }
}