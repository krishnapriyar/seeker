package com.seeker.seeker.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewbinding.ViewBinding
import com.seeker.seeker.R
import com.seeker.seeker.databinding.AppliedJobItemBinding
import com.seeker.seeker.databinding.JobListItemBinding
import com.seeker.seeker.model.ui.Job

class JobsAdapter(val jobsList:List<Job>, val type: JobListViewType, val itemClickListener: OnItemClickListener): RecyclerView.Adapter<JobsAdapter.JobItemViewHolder>() {

    inner class JobItemViewHolder(val binding: ViewBinding) :ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobItemViewHolder {

        val binding = when(type){
            JobListViewType.HOME -> JobListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            JobListViewType.APPLIED -> AppliedJobItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        }

        return JobItemViewHolder(binding)
    }

    override fun getItemCount(): Int = jobsList.size

    override fun onBindViewHolder(holder: JobItemViewHolder, position: Int) {

        with(holder){
            with(jobsList[position]) {
                if (type == JobListViewType.HOME){
                    binding as JobListItemBinding
                    binding.textJobTitle.text = jobTitle
                    binding.textJobDescription.text = jobDescription
                    binding.textCompanyName.text = companyName
                }else{
                    binding as AppliedJobItemBinding
                    binding.textJobTitle.text = jobTitle
                    binding.textJobDescription.text = jobDescription
                    binding.textCompanyName.text = companyName
                    binding.textDateApplied.text = holder.itemView.context.getString(R.string.applied_on, dateApplied)
                }
                holder.itemView.setOnClickListener {
                    itemClickListener.onJobItemClicked(this)
                }
            }
        }
    }
}