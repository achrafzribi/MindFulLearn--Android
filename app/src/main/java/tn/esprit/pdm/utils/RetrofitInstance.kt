package tn.esprit.pdm.utils

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "http://10.0.2.2:9090"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    val experienceService: ExperienceServices by lazy {
        retrofit.create(ExperienceServices::class.java)
    }

    val communityService: CommunityServices by lazy {
        retrofit.create(CommunityServices::class.java)
    }

    fun createApiService(): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    fun createExperienceService(): ExperienceServices {
        return retrofit.create(ExperienceServices::class.java)
    }

    fun createCommunityService(): CommunityServices {
        return retrofit.create(CommunityServices::class.java)
    }
}