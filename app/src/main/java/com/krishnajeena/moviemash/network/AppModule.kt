package com.krishnajeena.moviemash.network
import com.krishnajeena.moviemash.models.DetailsViewModel
import com.krishnajeena.moviemash.models.HomeViewModel
import com.krishnajeena.moviemash.repository.MovieMashRepository
import com.krishnajeena.moviemash.repoimpl.MovieMashRepositoryImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import org.koin.core.module.dsl.*
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {


    single { provideRetrofit() }

    single { provideApiService(get()) }

    single<MovieMashRepository> { MovieMashRepositoryImpl(get()) }

    viewModel { HomeViewModel(get()) }
    viewModel{ DetailsViewModel(get()) }
}
//val loggingInterceptor = HttpLoggingInterceptor().apply {
//    level = HttpLoggingInterceptor.Level.BODY
//}
//
//val okHttpClient = OkHttpClient.Builder()
//    .addInterceptor(loggingInterceptor)
//    .build()


fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://api.watchmode.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
       // .client(okHttpClient)
        .build()
}

fun provideApiService(retrofit: Retrofit): ApiService {
    return retrofit.create(ApiService::class.java)
}
