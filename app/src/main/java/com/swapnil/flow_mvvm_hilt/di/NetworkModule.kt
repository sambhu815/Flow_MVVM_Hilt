package com.swapnil.flow_mvvm_hilt.di

import com.swapnil.flow_mvvm_hilt.data.remote.StoreServices
import com.swapnil.flow_mvvm_hilt.utils.Constant.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideStandardDispatchers(): StandardDispatchers =
        object : StandardDispatchers {
            override val ioDispatchers: CoroutineDispatcher
                get() = Dispatchers.IO
            override val defaultDispatcher: CoroutineDispatcher
                get() = Dispatchers.Default
            override val mainDispatcher: CoroutineDispatcher
                get() = Dispatchers.Main
            override val unconfinedDispatcher: CoroutineDispatcher
                get() = Dispatchers.Unconfined

        }


    interface StandardDispatchers {
        val ioDispatchers: CoroutineDispatcher
        val defaultDispatcher: CoroutineDispatcher
        val mainDispatcher: CoroutineDispatcher
        val unconfinedDispatcher: CoroutineDispatcher
    }

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient
            .Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create()

    @Singleton
    val provideHttpClient: OkHttpClient.Builder = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.MINUTES)
        .writeTimeout(1, TimeUnit.MINUTES)
        .readTimeout(1, TimeUnit.MINUTES)
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))

    @Singleton
    @Provides
    fun provideRetrofit(
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(provideHttpClient.build())
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Singleton
    @Provides
    fun provideStoreService(retrofit: Retrofit): StoreServices =
        retrofit.create(StoreServices::class.java)
}