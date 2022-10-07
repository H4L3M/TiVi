package com.nokhbativi.network

import com.nokhbativi.model.network.NetworkLiveEvent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import javax.inject.Singleton

const val SoccerBaseUrl = "https://api.sofascore.com/api/v1/"
private const val LIVE_EVENTS = "sport/football/events/live"

const val TiViBaseUrl = "https://github.com/H4L3M/iptv-api/raw/main/"

interface RetroNetSoccerApi {
    @GET(LIVE_EVENTS)
    suspend fun getLiveEvents(): NetworkLiveEventsContainer

}

data class NetworkLiveEventsContainer(
    val events: List<NetworkLiveEvent>
)

@Singleton
object RetroNet {
    val networkSoccerApi: RetroNetSoccerApi = Retrofit.Builder()
        .baseUrl(SoccerBaseUrl)
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
        .create(RetroNetSoccerApi::class.java)
}