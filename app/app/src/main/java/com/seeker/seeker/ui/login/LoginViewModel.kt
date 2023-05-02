package com.seeker.seeker.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seeker.seeker.data.MockData
import com.seeker.seeker.repositories.UserRepository
import com.seeker.seeker.ui.login.model.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(val userRepository: UserRepository) : ViewModel(){
    private val loginToken = MutableLiveData<String?>()
    val loginTokenObserver: LiveData<String?> = loginToken

    private val loginState = MutableLiveData<LoginState>()
    val loginStateObserver: LiveData<LoginState> = loginState

    fun login(username: String, password: String) {
        viewModelScope.launch {
            val token = userRepository.login(username, password)
            if (token!=null){
                saveLoginToken(token)
                loginState.postValue(LoginState.LOGGED_IN)
            }else{
                loginState.postValue(LoginState.ERROR)
            }
        }
    }

    fun checkExistingLogin(){
        if (isLoggedIn()){
            loginState.postValue(LoginState.LOGGED_IN)
        }else (
            loginState.postValue(LoginState.LOGGED_OUT)
        )
    }

    private fun saveLoginToken(token: String) = userRepository.saveLoginToken(token)

    fun isLoggedIn(): Boolean{
        return userRepository.getLoginToken() != null
    }

    fun login(){

    }
}