package com.seeker.seeker.repositories

import com.seeker.seeker.data.MockData
import com.seeker.seeker.service.JobsService
import java.lang.Exception
import javax.inject.Inject

class JobsRepository @Inject constructor(val jobsService: JobsService) {

    suspend fun applyJob(jobId: String): Boolean{
        return try{
            jobsService.apply(jobId)
        }catch (ex: Exception){
            false
        }
    }

}