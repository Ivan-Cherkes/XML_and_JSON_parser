
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "root", strict = false)
class Root {
    @field:ElementList(name = "news", required = false)
    lateinit var news: List<Element>
}

@Root(name = "element", strict = false)
class Element {

    @field:org.simpleframework.xml.Element(name = "date", required = false)
    var date: String? = null

    @field:org.simpleframework.xml.Element(name = "description", required = false)
    var description: String? = null

    @field:org.simpleframework.xml.Element(name = "title", required = false)
    var title: String? = null

    @field:ElementList(name = "keywords", required = false)
    lateinit var keywordsList: List<String>

}
