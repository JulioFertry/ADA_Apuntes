package actividadesXML.ejerCRUD

import actividadesXML.ejerCRUD.model.Empleado
import actividadesXML.ejerCRUD.repository.XMLRepository
import java.nio.file.Path

fun main() {

    val repository = XMLRepository()

    val ficheroXML = Path.of("src")
        .resolve("main")
        .resolve("resources")
        .resolve("archivosXML")
        .resolve("archivosCRUD")
        .resolve("empleados.xml")

    val emple = Empleado("16", "Linares", "IT", 5000.0)

    repository.insert(emple, ficheroXML)

}