package actividadesXML.act3

import org.w3c.dom.Element
import org.w3c.dom.Node
import java.nio.file.Path
import java.text.SimpleDateFormat
import java.util.Date
import javax.xml.parsers.DocumentBuilderFactory


fun getReportList(newsFile: Path): MutableList<Report> {
    val reportList = mutableListOf<Report>()

    val docBuilderFactory = DocumentBuilderFactory.newInstance()
    val docBuilder = docBuilderFactory.newDocumentBuilder()
    val reportDoc = docBuilder.parse(newsFile.toFile())

    val root = reportDoc.documentElement
    root.normalize()

    val reports = root.getElementsByTagName("item")
    val dateFormat = SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", java.util.Locale.ENGLISH)

    for (i in 0..<reports.length) {

        val node = reports.item(i)
        if (node.nodeType == Node.ELEMENT_NODE) {
            val elementNode = node as Element

            val titleElement = elementNode.getElementsByTagName("title")
            val linkElement = elementNode.getElementsByTagName("link")
            val guidElement = elementNode.getElementsByTagName("guid")
            val pubDateElement = elementNode.getElementsByTagName("pubDate")
            val descriptionElement = elementNode.getElementsByTagName("description")

            val textContentTitle = titleElement.item(0).textContent
            val textContentLink = linkElement.item(0).textContent
            val textContentGuid = guidElement.item(0).textContent
            val textContentPubDate = pubDateElement.item(0).textContent
            val textContentDescription = descriptionElement.item(0).textContent

            val parsedPubDate: Date = dateFormat.parse(textContentPubDate)

            val report = Report(textContentTitle, textContentLink, textContentGuid, parsedPubDate, textContentDescription)
            reportList.add(report)
        }
    }

    return reportList
}

fun main() {
    val reportFile = Path.of("src")
        .resolve("main")
        .resolve("resources")
        .resolve("archivosXML")
        .resolve("agencia_tributaria.xml")

    val reports = getReportList(reportFile)

    for (report in reports) {
        println(report)
    }

}