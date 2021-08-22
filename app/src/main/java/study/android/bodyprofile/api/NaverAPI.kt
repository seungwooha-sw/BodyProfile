package study.android.bodyprofile.api

import retrofit2.http.*
import study.android.bodyprofile.api.model.SearchNewsResponse
import study.android.bodyprofile.api.model.TransferPapagoResponse


interface NaverAPI {
    @GET("v1/search/news.json")
    suspend fun getSearchNews(
        @Query("query") query: String,
        @Query("display") display: Int? = null,
        @Query("start") start: Int? = null
    ): SearchNewsResponse

    @FormUrlEncoded
    @POST("v1/papago/n2mt")
    suspend fun transferPapago(
        @Field("source") source: String,
        @Field("target") target: String,
        @Field("text") text: String
    ): TransferPapagoResponse
}