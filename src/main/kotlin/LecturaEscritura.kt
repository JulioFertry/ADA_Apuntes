import java.io.BufferedReader
import java.io.BufferedWriter
import java.nio.file.Files
import java.nio.file.Path

fun main() {

    //BUFFERED READER
    val ficheroPrueba = Path.of("src/main/resources/prueba.txt")

    //Vamos a explorar cómo leer un archivo con BufferedReader
    //Debemos crear/abrir un flujo de lectura de tipo BufferedReader
    val br: BufferedReader = Files.newBufferedReader(ficheroPrueba)

    //Una vez tenemos el flujo de lectura abierto, podemos consumirlo
    //br.forEachLine { line -> println(line) }
    //IMPORTANTE cerrar el flujo!
    //br.close()

    //Una forma más segura de recorrer todas las líneas es usando .use
    //.use asegura que aunque haya excepciones, se cierre el flujo
    br.use { flujo -> flujo.forEachLine { println(it) } }


    //BUFFERED WRITER
    val ficheroPrueba2 = Path.of("src/main/resources/prueba2.txt")

    //Crear/Abrir un flujo de escritura BufferedWriter
    val bw: BufferedWriter = Files.newBufferedWriter(ficheroPrueba2)

    //.use para asegurar que liberamos el recurso
    bw.use { flujo ->
        flujo.write("Adios mundo\n")
        flujo.write("Monos con traje")
    }

}