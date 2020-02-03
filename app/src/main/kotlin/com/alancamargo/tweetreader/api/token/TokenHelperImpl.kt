package com.alancamargo.tweetreader.api.token

import com.alancamargo.tweetreader.BuildConfig.CONSUMER_KEY
import com.alancamargo.tweetreader.BuildConfig.CONSUMER_SECRET
import com.alancamargo.tweetreader.api.AuthenticationApi
import com.alancamargo.tweetreader.helpers.PreferenceHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Credentials
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TokenHelperImpl(
    private val preferenceHelper: PreferenceHelper,
    private val baseUrl: String
) : TokenHelper {

    override suspend fun getAccessTokenAndUpdateCache(): String {
        val cachedToken = preferenceHelper.getAccessToken()

        return if (cachedToken.isNullOrEmpty()) {
            val tempCredentials = Credentials.basic(
                CONSUMER_KEY,
                CONSUMER_SECRET
            )

            val authenticationApi = buildAuthenticationApi()

            withContext(Dispatchers.IO) {
                authenticationApi.postCredentials(tempCredentials).getAuthorisationHeader().also {
                    preferenceHelper.setAccessToken(it)
                }
            }
        } else {
            cachedToken
        }
    }

    private fun buildAuthenticationApi(): AuthenticationApi {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthenticationApi::class.java)
    }

}