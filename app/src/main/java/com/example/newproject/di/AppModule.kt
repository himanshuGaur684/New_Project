package com.example.newproject.di

import android.content.Context
import com.example.newproject.network.api.ApiService
import com.example.newproject.repository.MainRepository
import com.example.newproject.room.MainDAO
import com.example.newproject.room.MainDatabase
import com.google.gson.Gson
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
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://run.mocky.io/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context): MainDatabase {
        return MainDatabase.getInstance(context)
    }

    @Provides
    fun provideMainDAO(mainDatabase: MainDatabase): MainDAO {
        return mainDatabase.getMainDao()
    }

    @Provides
    fun provideMainRepository(apiService: ApiService, dao: MainDAO): MainRepository {
        return MainRepository(apiService = apiService, dao = dao)
    }

}