package actividadesXML.act4

import java.io.File
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.OutputKeys
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult


object Writer {

    private val docBuilderFactory = DocumentBuilderFactory.newInstance()
    private val docBuilder = docBuilderFactory.newDocumentBuilder()
    private val implementation = docBuilder.domImplementation


    fun writeNewProducts(saveFile: File, newProducts: List<Product>) {
        val document = implementation.createDocument(null, "productos", null)

        for (product in newProducts) {
            val productElement = document.createElement("producto")
            document.documentElement.appendChild(productElement)

            val name = document.createElement("nombre")
            val price = document.createElement("precio")

            val nameText = document.createTextNode(product.name)
            val priceText = document.createTextNode(product.price.toString())

            name.appendChild(nameText)
            price.appendChild(priceText)

            productElement.appendChild(name)
            productElement.appendChild(price)
        }

        val source = DOMSource(document)
        val result = StreamResult(saveFile)
        val transformer = TransformerFactory.newInstance().newTransformer()

        transformer.setOutputProperty(OutputKeys.INDENT, "yes")
        transformer.transform(source, result)
    }


}