import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class JsonModelResponse(
    @Json(name = "news") val news: List<NewsListJson>
)

@JsonClass(generateAdapter = true)
data class NewsListJson(
    @Json(name = "title") val title: String?,
    @Json(name = "description") val description: String,
    @Json(name = "date") val date: String,
    @Json(name = "keywords") val keywords: List<String>
)

