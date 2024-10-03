package apuntesXML

import org.w3c.dom.DOMImplementation
import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.Text
import java.nio.file.Path
import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.OutputKeys
import javax.xml.transform.Result
import javax.xml.transform.Source
import javax.xml.transform.Transformer
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult

fun main() {

    // Escritura de Archivos XML

    // 1º Instanciar la clase DocumentBuilderFactory
    val factory: DocumentBuilderFactory = DocumentBuilderFactory.newInstance()

    // Instanciar la clase DocumentBuilder
    val builder: DocumentBuilder = factory.newDocumentBuilder()

    // (Opcional) DOMImplementation contiene los métodos para crear un Document
    val imp: DOMImplementation = builder.domImplementation

    // 2º Crear un Document vacío (namespaceURI, qualifiedName, doctype)
    val docu: Document = imp.createDocument(null, "productos", null)

    // En este punto, ya tenemos el primer "Element" creado. El nodo root.
    // 3º Solo tenemos que "append"(adjuntar) hijos al nodo root

    /**     <productos>
     *          <producto>
     *              <nombre>
     *              <precio>
     *          </producto>
     *          <producto>
     *              ....
     *              ....
     *      ....
     *      ....
     *      </productos>
     */

    // a) Primero creamos el Element
    val producto1: Element = docu.createElement("productos")
    docu.documentElement.appendChild(producto1)

    // b) Hijos de producto1
    val nombreP1: Element = docu.createElement("nombre")
    val precioP1: Element = docu.createElement("precio")

    val textoNombreP1: Text = docu.createTextNode("Agua")
    val textoPrecioP1: Text = docu.createTextNode("1.50")

    // Unimos el textNode al elemento correspondiente
    nombreP1.appendChild(textoNombreP1)
    precioP1.appendChild(textoPrecioP1)

    // Unimos el nombre y el precio al producto
    producto1.appendChild(nombreP1)
    producto1.appendChild(precioP1)

    // Añadimos todos los nodos que queramos

    // 4º Crear el XML
    // -> ¿Qué queremos escribir?
    val source: Source = DOMSource(docu)

    // -> Qué clase usamos para escribir: StreamResult()
    val result: Result = StreamResult(Path.of("src/main/resources/archivosXML/productosWrite.xml").toFile())

    // -> Qué herramienta usamos para realizar la transformación: Transformer
    val transformer: Transformer = TransformerFactory.newInstance().newTransformer()

    // BONUS
    // Para identar el XML correctamente
    transformer.setOutputProperty(OutputKeys.INDENT, "yes")

    // Por ultimo realizamos la transformación
    transformer.transform(source, result)
}