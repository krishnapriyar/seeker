package com.seeker.seeker.repositories

import com.seeker.seeker.data.MockData
import com.seeker.seeker.data.UserLocalDataSource
import com.seeker.seeker.service.UserService
import java.lang.Exception
import javax.inject.Inject

class UserRepository @Inject constructor(private val userLocalDataSource: UserLocalDataSource, val userService: UserService) {

    fun saveLoginToken(token:String) = userLocalDataSource.saveLoginToken(token)
    fun getLoginToken():String? = userLocalDataSource.getLoginToken()
    fun removeLoginToken() = userLocalDataSource.saveLoginToken(null)

    suspend fun login(username: String, password: String): String?{
        return try{
            userService.login(username, password)
        }catch (ex:Exception){
            MockData.getUser(username, password)
        }
    }
}