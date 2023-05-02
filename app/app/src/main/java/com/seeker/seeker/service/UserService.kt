package com.seeker.seeker.service

import com.apollographql.apollo3.ApolloClient
import com.seeker.seeker.LoginMutation
import javax.inject.Inject

class UserService @Inject constructor(val apolloClient: ApolloClient) {
    suspend fun login(username: String, password: String): String? {
        val result = apolloClient.mutation(LoginMutation(username, password)).execute()
        return result.data?.auth
    }
}