package com.seeker.seeker.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.seeker.seeker.data.MockData
import com.seeker.seeker.model.ui.Job

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    private val jobs = MutableLiveData<List<Job>>()
    val jobsObserver: LiveData<List<Job>> = jobs

    fun getJobList(){
        jobs.postValue(MockData.getJobsList())
    }
}