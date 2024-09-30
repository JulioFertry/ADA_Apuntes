import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardCopyOption
import kotlin.io.path.exists
import kotlin.io.path.notExists

fun main() {
    //1º objeto Path
    val raiz = Path.of("src")
    println(raiz)
    val rutaFich = raiz.resolve("main").resolve("resources").resolve("prueba.txt")
    println(rutaFich)

    val rutaDestino = raiz.resolve("main").resolve("resources").resolve("carpetaPrueba").resolve("prueba.txt")

    if (rutaDestino.notExists()) {
        //Creo los directorios hasta la ruta destino
        Files.createDirectories(rutaDestino.parent)
        //Creo el fichero documentoDestino.txt
        Files.createFile(rutaDestino)
    }

    //comprobar si ese archivo existe
    if (rutaFich.exists()) {
        Files.copy(rutaFich, rutaDestino, StandardCopyOption.REPLACE_EXISTING)
    }
}