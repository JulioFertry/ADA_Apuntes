package actividadesXML.act4

import org.w3c.dom.NodeList
import java.nio.file.Path
import javax.xml.parsers.DocumentBuilderFactory
import kotlin.io.path.exists

class Reader {

    private val docBuilderFactory = DocumentBuilderFactory.newInstance()
    private val docBuilder = docBuilderFactory.newDocumentBuilder()


    fun getProdcuts(productFile: Path): NodeList {

        if (productFile.exists()) {
            val document = docBuilder.parse(productFile.toFile())
            val root = document.documentElement
            root.normalize()

            val productList = root.getElementsByTagName("producto")
            return productList

        } else {
            throw IllegalArgumentException(" - No existe el archivo")
        }

    }


}