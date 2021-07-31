package study.android.bodyprofile

import android.util.Log
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import study.android.bodyprofile.api.NaverAPI
import study.android.bodyprofile.api.model.SearchNewsResponse


const val CLIENT_ID = "qr9OpkXILauYV7Az3Wl5"
const val CLIENT_SECRET = "VEsaVSATv5"
const val BASE_URL_NAVER_API = "https://openapi.naver.com"

const val TAG = "Log : retrofitManager"

class RetrofitManager(baseUrl: String) {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    companion object {
        fun createRetrofitForNaverApi() : RetrofitManager{
            return RetrofitManager(BASE_URL_NAVER_API)
        }
    }

    suspend fun testNews() {
        val api = retrofit.create(NaverAPI::class.java)

        // TODO why api.getSearchNews returns Result<Int>..? not SearchNewsResponse
        val getSearchNewsResponse = api.getSearchNews(CLIENT_ID, CLIENT_SECRET, "테스트").runCatching {
            Log.d(TAG, this.toString())
        }.onFailure {
            Log.d(TAG, "failed")
        }
        Log.d(TAG, getSearchNewsResponse.toString())
    }

    suspend fun testPapago() {
        val api = retrofit.create(NaverAPI::class.java)

        val getTransferPapagoResponse = api.transferPapago(CLIENT_ID, CLIENT_SECRET,
            "ko", "en", "테스트입니다. 이거 번역해주세요.").runCatching {
            Log.d(TAG, this.toString())
        }.onFailure {
            Log.d(TAG, "failed")
        }

        Log.d(TAG, getTransferPapagoResponse.toString())
    }
}