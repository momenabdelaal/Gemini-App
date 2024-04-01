package com.mobly.app.core.di.network

import android.app.Application
import android.content.Context
import com.mobly.app.core.util.Constants.BASE_URL
import com.mobly.app.core.di.error.ErrorTypeHandler
import com.mobly.app.core.di.error.ErrorTypeHandlerImpl
import com.mobly.app.data.dataSource.network_apis.ServicesAPIs
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
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideContext(application: Application): Context = application.applicationContext


    @Singleton
    @Provides
    fun provideApiService(): ServicesAPIs {

        val httpClient = OkHttpClient.Builder()
            .addInterceptor{ chain ->
                chain.proceed(chain.request().newBuilder().build())
            }.also { client ->

                val logging = HttpLoggingInterceptor()
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                client.addInterceptor(logging)
                client.connectTimeout(30, TimeUnit.SECONDS)
                client.readTimeout(30, TimeUnit.SECONDS)
                client.writeTimeout(30, TimeUnit.SECONDS)
            }.build()


        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient).build()

        return retrofit.create(ServicesAPIs::class.java)
    }

    @Provides
    fun provideErrorTypeHandler(errorTypeHandlerImpl: ErrorTypeHandlerImpl): ErrorTypeHandler {
        return errorTypeHandlerImpl
    }

    @DefaultDispatcher
    @Provides
    fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @IoDispatcher
    @Provides
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @MainDispatcher
    @Provides
    fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main
    @Retention(AnnotationRetention.BINARY)
    @Qualifier
    annotation class DefaultDispatcher

    @Retention(AnnotationRetention.BINARY)
    @Qualifier
    annotation class IoDispatcher

    @Retention(AnnotationRetention.BINARY)
    @Qualifier
    annotation class MainDispatcher
}

