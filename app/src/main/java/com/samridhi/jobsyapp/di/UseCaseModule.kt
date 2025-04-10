package com.samridhi.jobsyapp.di

import com.samridhi.jobsyapp.data.network.repositories.AppRepositories
import com.samridhi.jobsyapp.domain.JobDataRemoteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
 object UseCaseModule {

    @Provides
    @ActivityRetainedScoped
    fun provideJobDataUseCase(appRepositories: AppRepositories) = JobDataRemoteUseCase(appRepositories)
}