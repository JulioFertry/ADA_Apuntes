package actividadesXML.ejerCRUD.repository

import actividadesXML.ejerCRUD.model.Empleado
import org.w3c.dom.Document
import java.nio.file.Path
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.OutputKeys
import javax.xml.transform.Source
import javax.xml.transform.Transformer
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult
import javax.xml.transform.stream.StreamSource
import kotlin.io.path.notExists

/*
 Clase que Implementa los métodos CRUD
*/
class XMLRepository {

    /** Parsea documentos xml
     *
     * @param rutaXML ruta del documento a parsear
     *
     * @return Documento parseado
     */
    private fun parseXML(rutaXML: Path): Document {
        val dbf = DocumentBuilderFactory.newInstance()
        val db = dbf.newDocumentBuilder()
        return db.parse(rutaXML.toFile())
    }


    /**
     * C -> Crea / Inserta un nuevo empleado
     *
     * @param newEmpleado empleado
     * @param rutaXML ruta del archivo xml
     */
    fun insert(newEmpleado: Empleado, rutaXML: Path) {

        if (rutaXML.notExists()) {
            throw Exception("Ruta XML no existe")
        }

        if (
            newEmpleado.id.isBlank()
            || newEmpleado.apellido.isBlank()
            || newEmpleado.departamento.isBlank()
            ) {

            throw Exception("Atributos incorrectos")

        }

        // Parseo del documento
        val document = parseXML(rutaXML)
        val root = document.documentElement
        root.normalize()

        // Insertar empleado
        val elementoNuevoEmpleado = document.createElement("empleado")
        elementoNuevoEmpleado.setAttribute("id", newEmpleado.id)

        root.appendChild(elementoNuevoEmpleado)

        val elementoApellido = document.createElement("apellido")
        val elementoDpto = document.createElement("departamento")
        val elementoSalario = document.createElement("salario")

        elementoNuevoEmpleado.setAttribute("id", newEmpleado.id)
        val textNodeApellido = document.createTextNode(newEmpleado.apellido)
        val textNodeDpto = document.createTextNode(newEmpleado.departamento)
        val textNodeSalario = document.createTextNode(newEmpleado.salario.toString())

        elementoApellido.appendChild(textNodeApellido)
        elementoDpto.appendChild(textNodeDpto)
        elementoSalario.appendChild(textNodeSalario)

        elementoNuevoEmpleado.appendChild(elementoApellido)
        elementoNuevoEmpleado.appendChild(elementoDpto)
        elementoNuevoEmpleado.appendChild(elementoSalario)

        // Transformacion del arbol a XML
        val source: Source = DOMSource(document)
        val result = StreamResult(rutaXML.toFile())
        val xslt: Source = StreamSource(Path.of("src/main/resources/archivosXML/archivosCRUD/Style.xsl").toFile())
        val transformer: Transformer = TransformerFactory.newInstance().newTransformer(xslt)

        transformer.setOutputProperty(OutputKeys.INDENT, "yes")
        transformer.setOutputProperty(OutputKeys.METHOD, "xml")
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no")

        transformer.transform(source, result)
    }

}