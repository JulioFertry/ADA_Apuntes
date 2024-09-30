package actividadesXML

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
        //
    }

}