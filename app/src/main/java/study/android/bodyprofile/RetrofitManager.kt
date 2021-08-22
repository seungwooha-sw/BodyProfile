package study.android.bodyprofile

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import study.android.bodyprofile.api.NaverAPI
import study.android.bodyprofile.api.model.SearchNewsResponse
import study.android.bodyprofile.api.model.TransferPapagoResponse


const val CLIENT_ID = "qr9OpkXILauYV7Az3Wl5"
const val CLIENT_SECRET = "VEsaVSATv5"
const val BASE_URL_NAVER_API = "https://openapi.naver.com"

const val TAG = "Log : retrofitManager"

class RetrofitManager(baseUrl: String) {
    private val retrofit: Retrofit

    companion object {
        fun createRetrofitForNaverApi(): RetrofitManager {
            return RetrofitManager(BASE_URL_NAVER_API)
        }
    }

    init {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor { chain ->
            val original = chain.request()

            val requestBuilder = original.newBuilder()
                .header("X-Naver-Client-Id", CLIENT_ID)
                .header("X-Naver-Client-Secret", CLIENT_SECRET)

            val request = requestBuilder.build()
            chain.proceed(request)
        }

        httpClient.addNetworkInterceptor(logging)

        val okHttpClient = httpClient.build()

        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    suspend fun testNews(): SearchNewsResponse? {
        val api = retrofit.create(NaverAPI::class.java)

        api.getSearchNews("테스트").runCatching {
            Log.d(TAG, this.toString())
            return this
        }.onFailure {
            Log.d(TAG, "failed")
        }

        return null
    }

    suspend fun testPapago(): TransferPapagoResponse? {
        val api = retrofit.create(NaverAPI::class.java)

        api.transferPapago("ko", "en", "테스트입니다. 이거 번역해주세요."
        ).runCatching {
            Log.d(TAG, this.toString())
            return this
        }.onFailure {
            Log.d(TAG, "failed")
        }

        return null
    }
}