package apuntesXML

import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import java.nio.file.Path
import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory


fun main() {
    // LECTURA DE FICHERO XML
    // EL OBJETIVO ES PARSEAR UN FICHERO XML A ARBOL DOM

    // 1º Instanciar un objeto DocoumentBuilderFactory
    val dbf: DocumentBuilderFactory = DocumentBuilderFactory.newInstance()

    // 2º Con el dbf instanciado, creamos un objeto de tipo DocumentBuilder
    val db: DocumentBuilder = dbf.newDocumentBuilder()

    // 3º Con el db, ya podemos parsear nuestro documento en texto plano a DOM
    // (Primero necesitamos un File)
    val fichero = Path.of("src").resolve("main/resources/archivosXML/productos.xml")
    val document = db.parse(fichero.toFile())

    // Dentro de la clase Document, tenemos un método importante:
    // 1. para obtener el elemento root
    val root: Element = document.documentElement

    // Dentro de la clase Element tenemos varios métodos importantes:
    // 1. para normalizar el arbol (quitar huecos blanco y mierdas)
    root.normalize()

    // 2. Para obtener todos los elementos por su nombre de etiqueta
    // (Se obtiene t_odo lo que contiene el elemento desde que abre hasta que cierra etiqueta)
    val listaNodos: NodeList = root.getElementsByTagName("producto")

    // Cuando tenemos la nodelist, podemos iterar sobre ella
    for (i in 0..<listaNodos.length){

        // Para acceder a un item en particular, accedemos a través del índice
        val nodo: Node = listaNodos.item(i)

        // Para acceder al tipo del nodo, usamos .nodeType()
        if (nodo.nodeType == Node.ELEMENT_NODE){ // Element en este caso
            // Casteamos a Element
            val nodoElemento = nodo as Element

            // Podemos buscar los elementos que nos conviene
            val elementoNombre = nodoElemento.getElementsByTagName("nombre")
            val elementoPrecio = nodoElemento.getElementsByTagName("precio")

            // Una vez tenemos el elemento que qeremos, podemios acceder a su contenido
            val textoContentNombre = elementoNombre.item(0).textContent
            val textoContentprecio = elementoPrecio.item(0).textContent.toDouble()

            // Imprimir
            println("Item: ${i + 1} - Producto: $textoContentNombre - Precio: $textoContentprecio")
        }
    }

}