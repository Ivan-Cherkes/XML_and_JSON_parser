fun main() {

    val getNews = GetNews()

    print("Введите значение 1 для скачивания Json новостей или 2 для XML = ")
    when (readLine()!!.toInt()) {
        1 -> getNews.getNewsJson()
        2 -> getNews.getNewsXml()
        else -> return print("Значения не правильные")
    }

    print(
        "Введите - 1 что бы показать все новости; " +
                "2 сортировать новости по заголовкам; " +
                "3 сортировать новости по ключевому слову = "
    )
    when (readLine()!!.toInt()) {
        1 -> getNews.getAllNews(getNews.newsList!!)
        2 -> getNews.sortNewsByTitles(getNews.newsList!!)
        3 -> run {
            getNews.sortNewsByKeywords(getNews.newsList!!)
            print("введите ключевое слово что бы показать новости по нему = ")
            readLine()?.let { getNews.getNewsByKeywords(it, getNews.newsList!!) }
        }
        else -> return print("Значения не правильные")
    }

}
