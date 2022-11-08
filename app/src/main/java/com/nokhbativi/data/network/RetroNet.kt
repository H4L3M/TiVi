package com.nokhbativi.data.network

import com.nokhbativi.data.container.NetworkFeaturedEventsContainer
import com.nokhbativi.data.container.NetworkLiveEventsContainer
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

const val SoccerBaseUrl = "https://api.sofascore.com/api/v1/"
private const val LIVE_EVENTS = "sport/football/events/live"

const val TILES = "buzzer/tiles/ma"

interface RetroNetSoccerApi {
    @GET(LIVE_EVENTS)
    suspend fun getLiveEvents(): NetworkLiveEventsContainer

    @GET("odds/1/featured-events/football/{date}/3600")
    suspend fun getFeaturedEvents(@Query("date") date: String): NetworkFeaturedEventsContainer

    @GET(TILES)
    suspend fun getTiles(@Query("date") date: String): NetworkFeaturedEventsContainer
}

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