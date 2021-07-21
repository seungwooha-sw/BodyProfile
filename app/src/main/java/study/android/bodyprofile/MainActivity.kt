package study.android.bodyprofile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.*

const val TAG = "test_log"


const val CLIENT_ID = "qr9OpkXILauYV7Az3Wl5"
const val CLIENT_SECRET = "VEsaVSATv5"
const val BASE_URL_NAVER_API = "https://openapi.naver.com"

class MainActivity : AppCompatActivity(), CoroutineScope {
    private lateinit var job: Job

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        testCoroutine()
        testRetrofitNews()
    }

    fun testCoroutine() {
        Log.d(TAG, "Start Main Thread")
        GlobalScope.launch {
            delay(3000)
            Log.d(TAG, "in Coroutine ...")
        }
        Log.d(TAG, "End Main Thread")
    }

    fun testRetrofitNews() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL_NAVER_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(NaverAPI::class.java)
        val callGetSearchNews = api.getSearchNews(CLIENT_ID, CLIENT_SECRET, "테스트")

        callGetSearchNews.enqueue(object : Callback<ResultGetSearchNews> {
            override fun onResponse(
                call: Call<ResultGetSearchNews>,
                response: Response<ResultGetSearchNews>
            ) {
                Log.d(TAG, "성공 : ${response.raw()}")
            }

            override fun onFailure(call: Call<ResultGetSearchNews>, t: Throwable) {
                Log.d(TAG, "실패 : $t")
            }
        })

        val callPostTransferPapago = api.transferPapago(CLIENT_ID, CLIENT_SECRET,
            "ko", "en", "테스트입니다. 이거 번역해주세요.")

        callPostTransferPapago.enqueue(object : Callback<ResultTransferPapago> {
            override fun onResponse(
                call: Call<ResultTransferPapago>,
                response: Response<ResultTransferPapago>
            ) {
                Log.d(TAG, "성공 : ${response.raw()}")
            }

            override fun onFailure(call: Call<ResultTransferPapago>, t: Throwable) {
                Log.d(TAG, "실패 : $t")
            }
        })
    }
}


