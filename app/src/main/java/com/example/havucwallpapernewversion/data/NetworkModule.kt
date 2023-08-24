package com.example.havucwallpapernewversion.data

import android.content.Context
import android.util.Log
import com.example.havucwallpapernewversion.CoreLocalHelper
import com.example.havucwallpapernewversion.CoreLocalHelperImpl
import com.example.havucwallpapernewversion.data.local.LocalDS
import com.example.havucwallpapernewversion.data.local.LocalDSImpl
import com.example.havucwallpapernewversion.features.images.data.api.ImageService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    fun providerGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }
    @Provides
    @Singleton
    fun provideRetrofit(gsonConverterFactory: GsonConverterFactory,localHelper: LocalDS): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(Interceptor { chain ->
                val request: Request =
                    chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer ${localHelper.getAuthorizationKey()}")
                        .build()
                chain.proceed(request)
            })
            .addInterceptor(logging)
            .build()

        return Retrofit.Builder()
            .baseUrl("https://havucapps.com/")
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()


    }
    @Provides
    @Singleton
    fun provideRetrofitClient(@ApplicationContext context: Context,
    ): CoreLocalHelper {
        return CoreLocalHelperImpl(context)
    }
    @Singleton
    @Provides
    fun provideLocalDS(
        @ApplicationContext context: Context,
    ): LocalDS = LocalDSImpl(context)


}