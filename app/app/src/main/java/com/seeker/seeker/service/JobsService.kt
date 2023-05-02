package com.seeker.seeker.service

import com.apollographql.apollo3.ApolloClient
import com.seeker.seeker.ApplyMutation
import com.seeker.seeker.LoginMutation
import javax.inject.Inject

class JobsService @Inject constructor(val apolloClient: ApolloClient) {

    suspend fun apply(jobId: String): Boolean{
        val result = apolloClient.mutation(ApplyMutation(jobId)).execute()
        return result.data?.apply?: false
    }

}
