package actividadesXML.act4

import java.nio.file.Path


fun main() {

    val operationProcessor = Operations()

    val productsPath = Path.of("src")
        .resolve("main")
        .resolve("resources")
        .resolve("archivosXML")
        .resolve("productos.xml")

    val productsWithVATFile = Path.of("src")
        .resolve("main")
        .resolve("resources")
        .resolve("archivosXML")
        .resolve("productsWithVAT.xml")
        .toFile()


    try {
        val products = Reader.getProdcuts(productsPath)
        val productsWithVAT = operationProcessor.calculateProductsWithVAT(products)
        Writer.writeNewProducts(productsWithVATFile, productsWithVAT)
    } catch (e: Exception) {
        println("***ERROR*** ${e.message}")
    }

}