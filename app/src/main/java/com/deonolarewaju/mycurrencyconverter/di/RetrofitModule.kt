package com.deonolarewaju.mycurrencyconverter.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
class RetrofitModule {
//
//    @Provides
//    @Singleton
//    fun providesGsonBuilder(): Gson{
//        return GsonBuilder()
//            .create()
//    }
//
//    @Provides
//    @Singleton
//    fun providesRetrofitInstance(
//        @ApplicationContext context: Context,
//        gson: Gson
//    ): Retrofit {
//        val client = OkHttpClient.Builder()
//            .addNetworkInterceptor(StethoInterceptor())
//            .addInterceptor(NetworkInterceptors(context))
//            .build()
//
//        return Retrofit.Builder()
//            .baseUrl(Constants.URL.BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create(gson))
//            .client(client)
//            .build()
//    }
//
//    @Provides
//    @Singleton
//    fun getApi(retrofit: Retrofit): RetrofitApi = retrofit.create(
//        RetrofitApi::class.java)


}