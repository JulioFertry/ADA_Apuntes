package actividadesXML.act4

import org.w3c.dom.Element
import org.w3c.dom.Node
import java.nio.file.Path
import javax.xml.parsers.DocumentBuilderFactory
import kotlin.io.path.exists


object Reader {

    private val docBuilderFactory = DocumentBuilderFactory.newInstance()
    private val docBuilder = docBuilderFactory.newDocumentBuilder()

    private fun getRootElement(productFile: Path): Element {
        if (productFile.exists()) {
            val document = docBuilder.parse(productFile.toFile())
            val root = document.documentElement
            root.normalize()
            return root

        } else {
            throw IllegalArgumentException(" - No existe el archivo")
        }

    }


    fun getProdcuts(productFile: Path): MutableList<Product> {
        val root = getRootElement(productFile)
        val productList = mutableListOf<Product>()

        val productNodeList = root.getElementsByTagName("producto")
        for (i in 0 until productNodeList.length) {

            val node = productNodeList.item(i)
            if (node.nodeType == Node.ELEMENT_NODE) {
                val nodeElement = node as Element

                val nameElement = nodeElement.getElementsByTagName("nombre")
                val priceElement = nodeElement.getElementsByTagName("precio")

                val textContentName = nameElement.item(0).textContent
                val textContentPrice = priceElement.item(0).textContent.toFloat()

                val newProduct = Product(textContentName, textContentPrice)
                productList.add(newProduct)
            }
        }

        return productList
    }


}