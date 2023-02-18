package com.shahriar.a12_the_movie_db.di

import com.shahriar.a12_the_movie_db.api.ApiServices
import com.shahriar.a12_the_movie_db.utils.ConnectionInterceptor
import com.shahriar.a12_the_movie_db.utils.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideOkHTTP(connectionInterceptor: ConnectionInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(connectionInterceptor)
            .connectTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun providesRetrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
    }

    @Provides
    @Singleton
    fun providesApi(builder: Retrofit.Builder, okHttpClient: OkHttpClient): ApiServices {
        return builder.client(okHttpClient).build().create(ApiServices::class.java)
    }
}
