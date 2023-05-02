package com.seeker.seeker.ui.job

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seeker.seeker.repositories.JobsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JobViewModel @Inject constructor(val jobsRepository: JobsRepository) : ViewModel() {

    fun applyJob(jobId: String){
        viewModelScope.launch {
            jobsRepository.applyJob(jobId)
        }
    }
}