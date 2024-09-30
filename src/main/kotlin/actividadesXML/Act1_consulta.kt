package actividadesXML

import org.w3c.dom.Element
import org.w3c.dom.Node
import java.nio.file.Path
import javax.xml.parsers.DocumentBuilderFactory

fun main() {

    // Instanciar objeto DocumentBuilderFactory
    val docBuilderFactory = DocumentBuilderFactory.newInstance()

    // Crear objeto DocumentBuilde
    val docBuilder = docBuilderFactory.newDocumentBuilder()

    // Crear el File desde una ruta
    val fichero = Path.of("src")
        .resolve("main")
        .resolve("resources")
        .resolve("archivosXML")
        .resolve("empleados.xml")

    // Parseamos el documento a DOM usando el fichero anterior
    val document = docBuilder.parse(fichero.toFile())

    // Obtenemos el elemento root
    val root = document.documentElement
    // Normalizamos el arbol completo
    root.normalize()

    // Obtenemos los elementos empleado por su nombre de etiqueta
    val listaEmpleados = root.getElementsByTagName("empleado")

    // Recorremos los empleados
    for (i in 0..<listaEmpleados.length) {

        val nodo = listaEmpleados.item(i)
        if (nodo.nodeType == Node.ELEMENT_NODE) {
            // Casteamos a Element
            val nodoElemento = nodo as Element

            val elementoApellido = nodoElemento.getElementsByTagName("apellido")
            val elementoSalario = nodoElemento.getElementsByTagName("salario")

            val textContentApellido = elementoApellido.item(0).textContent
            val textContentSalario = elementoSalario.item(0).textContent.toFloat()

            // Mostramos los empleados que cobran 2 mil o mas lereles
            if (textContentSalario >= 2000) {
                println(textContentApellido)
            }

        }

    }

}