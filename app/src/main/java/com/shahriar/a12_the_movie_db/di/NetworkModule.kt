package com.shahriar.a12_the_movie_db.di

import com.shahriar.a12_the_movie_db.api.ApiServices
import com.shahriar.a12_the_movie_db.utils.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Provides
    @Singleton
    fun providesRetrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
    }

    @Provides
    @Singleton
    fun providesApi(builder: Retrofit.Builder): ApiServices {
        return builder.build().create(ApiServices::class.java)
    }
}
