package com.samridhi.jobsyapp.di

import android.content.Context
import androidx.room.Room
import com.google.gson.GsonBuilder
import com.samridhi.jobsyapp.data.local.db.dao.JobDao
import com.samridhi.jobsyapp.data.local.db.database.AppDatabase
import com.samridhi.jobsyapp.data.network.AppApiClientService
import com.samridhi.jobsyapp.util.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideAppApiClientService(): AppApiClientService =
        Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(AppApiClientService::class.java)

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "app_database").build()

    @Provides
    fun provideJobDao(database: AppDatabase): JobDao = database.jobDao()
}