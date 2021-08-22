package study.android.bodyprofile.api.model

data class SearchNewsResponse(
    val lastBuildDate: String = "",
    val total: Int = 0,
    val start: Int = 0,
    val display: Int = 0,
    val items: List<SearchedNewsItem>
)

data class SearchedNewsItem(
    val title: String = "",
    val originalLink: String = "",
    val link: String = "",
    val description: String = "",
    val pubDate: String = ""
)

