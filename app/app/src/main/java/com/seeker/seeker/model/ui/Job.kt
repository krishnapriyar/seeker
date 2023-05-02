package com.seeker.seeker.model.ui

import java.io.Serializable

data class Job(
    val _id: String,
    val jobTitle: String,
    val companyName: String,
    val jobDescription: String,
    val location: String? = null,
    val industry: String? = null,
    val salaryRange: SalaryRange? = null,
    val dateApplied: String? = null,
    val applied: Boolean = false
): Serializable {
    data class SalaryRange(
        val min: Int,
        val max: Int
    )
}



