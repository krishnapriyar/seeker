package com.seeker.seeker.data

import com.seeker.seeker.model.ui.Job
import com.seeker.seeker.ui.login.model.UserLogin

class MockData {

    companion object{
        fun getJobsList():List<Job> {
            val jobDescription = "This is a very brief description of the role's day-to-day responsibilities."
            return listOf(
                Job("1","Accounts Executive", "ABC Sdn Bhd", jobDescription, applied = true),
                Job("1","Operations Engineer", "DEF Sdn Bhd", jobDescription),
                Job("1","Customer Service Executive", "GHI Sdn Bhd", jobDescription),
                Job("1","General Manager", "JKL Sdn Bhd", jobDescription),
                Job("1","Sales Coordinator", "JKL Sdn Bhd", jobDescription),
                Job("1","L2 Tech Support Specialist", "MNO Sdn Bhd", jobDescription),
                Job("1","QA Engineer", "PQR Sdn Bhd", jobDescription),
                Job("1","Driver", "STU Sdn Bhd", jobDescription),
                Job("1","Admin Assistant", "VWX Sdn Bhd", jobDescription)
            )
        }

        fun getAppliedJobsList():List<Job> {
            val jobDescription = "This is a very brief description of the role's day-to-day responsibilities."
            val dateApplied = "21st April 2023"
            return listOf(
                Job("12","Assistant Manager ", "ABC Sdn Bhd", jobDescription, dateApplied = dateApplied, applied = true),
                Job("13","Store Manager", "DEF Sdn Bhd", jobDescription, dateApplied = dateApplied, applied = true),
                Job("14","Marketing Lead", "GHI Sdn Bhd", jobDescription, dateApplied = dateApplied, applied = true)
            )
        }

        fun getUser(username: String, password: String): String? {

            val usersLists = listOf(
                UserLogin("Seeker1123", "123456"),
                UserLogin("Seeker2123", "123456"),
                UserLogin("Seeker3123", "123456")
            )

           val user =  try{
               usersLists.first { it.password == password && it.username == username}
           }catch (ex:NoSuchElementException){
               null
           }

            return if (user != null){
                "JWT TOKEN FOR %${user.username}: qwer1234zxcv5678"
            }else{
                null
            }
        }
    }

}