package com.seeker.seeker.di

import android.app.Application
import android.content.Context
import com.apollographql.apollo3.ApolloClient
import com.seeker.seeker.SeekApplication
import com.seeker.seeker.data.UserLocalDataSource
import com.seeker.seeker.repositories.JobsRepository
import com.seeker.seeker.repositories.UserRepository
import com.seeker.seeker.service.JobsService
import com.seeker.seeker.service.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideApp(): Application = SeekApplication()

    @Singleton
    @Provides
    fun provideUserRepository(userLocalDataSource: UserLocalDataSource, userService: UserService): UserRepository = UserRepository(userLocalDataSource, userService)


    @Singleton
    @Provides
    fun provideJobsRepository(jobsService: JobsService): JobsRepository = JobsRepository(jobsService)


    @Singleton
    @Provides
    fun provideUserLocalDataSource(@ApplicationContext context: Context): UserLocalDataSource = UserLocalDataSource(context)

    @Singleton
    @Provides
    fun provideApolloClient(): ApolloClient{
        return ApolloClient.Builder()
            .serverUrl("https://example.com/graphql") //TODO: Change after BE is runnable
            .build()
    }

    @Singleton
    @Provides
    fun provideUserService(apolloClient: ApolloClient): UserService = UserService(apolloClient)

    @Singleton
    @Provides
    fun provideJobsService(apolloClient: ApolloClient): JobsService = JobsService(apolloClient)


}