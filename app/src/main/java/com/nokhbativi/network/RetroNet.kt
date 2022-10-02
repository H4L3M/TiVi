package com.nokhbativi.network

import com.google.gson.annotations.SerializedName
import com.nokhbativi.model.database.DatabaseChannel
import com.nokhbativi.model.network.Country
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import javax.inject.Singleton

const val BaseUrl = "https://github.com/H4L3M/iptv-api/raw/main/"
private const val CHANNELS = "arb.json"
private const val COUNTRIES = "countries"

interface RetroNetTiViApi {
    @GET(COUNTRIES)
    suspend fun getCountries(): NetworkCategoriesContainer

    @GET(CHANNELS)
    suspend fun getChannels(): Array<DatabaseChannel>
}
interface RetroNetSoccerApi {
    @GET(COUNTRIES)
    suspend fun getCountries(): NetworkCategoriesContainer

    @GET(CHANNELS)
    suspend fun getChannels(): Array<DatabaseChannel>
}

data class NetworkCategoriesContainer(
    @SerializedName(COUNTRIES)
    val countries: List<Country>,
)

@Singleton
object RetroNet {

    val networkTiViApi: RetroNetTiViApi = Retrofit.Builder()
        .baseUrl(BaseUrl)
        .client(
            OkHttpClient.Builder()
                .addInterceptor(
                    // TODO: Decide logging logic
                    HttpLoggingInterceptor().apply {
                        setLevel(HttpLoggingInterceptor.Level.BODY)
                    }
                )
                .build()
        )
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(RetroNetTiViApi::class.java)

    val networkSoccerApi: RetroNetTiViApi = Retrofit.Builder()
        .baseUrl(BaseUrl)
        .client(
            OkHttpClient.Builder()
                .addInterceptor(
                    // TODO: Decide logging logic
                    HttpLoggingInterceptor().apply {
                        setLevel(HttpLoggingInterceptor.Level.BODY)
                    }
                )
                .build()
        )
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(RetroNetTiViApi::class.java)
}