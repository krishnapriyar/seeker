package com.seeker.seeker.ui.applications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.seeker.seeker.data.MockData
import com.seeker.seeker.model.ui.Job

class ApplicationsViewModel : ViewModel() {

    private val applications = MutableLiveData<List<Job>>()
    val applicationsObserver: LiveData<List<Job>> = applications

    fun getAppliedJobsList(){
        applications.postValue(MockData.getAppliedJobsList())
    }
}