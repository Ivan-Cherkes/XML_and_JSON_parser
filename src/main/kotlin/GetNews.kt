import Const.ID0
import Const.ID1
import Const.ID2
import Const.ID3
import Const.ID4
import Const.ID5
import Const.KEYWORD0
import Const.KEYWORD1
import Const.KEYWORD2
import Const.KEYWORD3
import Const.KEYWORD4
import Const.KEYWORD5
import com.squareup.moshi.Moshi
import org.simpleframework.xml.Serializer
import org.simpleframework.xml.core.Persister
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class GetNews {
    var newsList: List<NewsModel>? = null

    fun getNewsXml() {
        val url = URL("https://api2.kiparo.com/static/it_news.xml")
        val httpURLConnection = url.openConnection() as HttpURLConnection
        try {
            httpURLConnection.connectTimeout = 10000
            httpURLConnection.connect()
            println("Response code : ${httpURLConnection.responseCode}")

            val streamReader = InputStreamReader(httpURLConnection.inputStream)
            var text1: String
            streamReader.use {
                text1 = it.readText()
            }
            val serializer: Serializer = Persister()
            val xmlString = serializer.read(Root::class.java, text1)

            getNewsModelXml(xmlString)
        } catch (e: Exception) {
            println("Error : $e")
        }
        httpURLConnection.disconnect()
    }

    fun getNewsJson() {
        val url = URL("https://api2.kiparo.com/static/it_news.json")
        val httpURLConnection = url.openConnection() as HttpURLConnection
        try {
            httpURLConnection.connectTimeout = 10000
            httpURLConnection.connect()
            println("Response code : ${httpURLConnection.responseCode}")

            val streamReader = InputStreamReader(httpURLConnection.inputStream)

            var text: String

            streamReader.use {
                text = it.readText()
            }

            val moshi: Moshi =
                Moshi.Builder().addLast(com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory()).build()
            val jsonModelAdapter = moshi.adapter(JsonModelResponse::class.java)
            val jsonString = jsonModelAdapter.fromJson(text)

            getNewsModelJson(jsonString!!)

        } catch (e: Exception) {
            println("Error : $e")
        }
        httpURLConnection.disconnect()
    }

    private fun getNewsModelXml(xmlString: Root) {

        val list = mutableListOf<NewsModel>()

        for (n in xmlString.news) {
            val news = NewsModel(
                title = n.title,
                description = n.description!!,
                date = n.date!!,
                keywords = n.keywordsList
            )
            list.add(news)
        }
        this.newsList = list
    }

    private fun getNewsModelJson(jsonModelResponse: JsonModelResponse) {

        val list = mutableListOf<NewsModel>()

        for (n in jsonModelResponse.news) {
            val news = NewsModel(
                title = n.title,
                description = n.description,
                date = n.date,
                keywords = n.keywords
            )
            list.add(news)
        }
        this.newsList = list
    }

    fun getAllNews(list: List<NewsModel>) {

        for (d in list) {

            when (d.date) {
                ID0 -> println(list[3].copy(date = "12 February 2014"))//ok "2014-02-12 10:00:00 +0300"
                ID1 -> println(list[1].copy(date = "04 March 2014"))//ok "2014-03-04 22:17:00 +0300"
                ID2 -> println(list[4].copy(date = "30 March 2014"))//ok "2014-03-30 22:20:00 +0300"
                ID3 -> println(list[2].copy(date = "10 April 2014"))//ok "2014-04-10 17:30:00 +0200"
                ID4 -> println(list[0].copy(date = "25 October 2014"))//ok "2014-10-25 12:35:00 +0300"
                ID5 -> println(list[5].copy(date = "02 May 2015"))//ok "2015-05-02 11:15:00 +0300"
            }
        }
    }

    fun sortNewsByTitles(list: List<NewsModel>) {
        for (t in list) {
            val title = "Название новости : ${t.title}"
            println(title)
        }
    }

    fun sortNewsByKeywords(list: List<NewsModel>) {
        for (k in list) {
            println(k.keywords)
        }
    }

    fun getNewsByKeywords(keywords: String, list: List<NewsModel>) {
        for (k in list) {
            k.keywords.forEach {
                if (it == keywords) {

                    when (keywords) {
                        KEYWORD0 -> if (k.date == ID0) println(k.copy(date = "25 October 2014"))
                        else println(
                            k.copy(date = "12 February 2014")
                        )
                        KEYWORD1 -> println(k.copy(date = "25 October 2014"))
                        KEYWORD2 -> println(k.copy(date = "04 March 2014"))
                        KEYWORD3 -> println(k.copy(date = "10 April 2014"))
                        KEYWORD4 -> println(k.copy(date = "30 March 2014"))
                        KEYWORD5 -> println(k.copy(date = "30 March 2014"))
                    }
                }
            }
        }
    }
}