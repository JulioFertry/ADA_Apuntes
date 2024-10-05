package actividadesXML.act4


class Operations {

    companion object {
        const val VAT = 21
    }


    private fun calculateVAT(productPrice: Float): Float {
        val vatMultiplier = 1.0f + (VAT / 100.0f)
        return productPrice * (vatMultiplier)
    }


    fun calculateProductsWithVAT(products: List<Product>): List<Product> {
        val productsWithVAT = mutableListOf<Product>()

        for (product in products) {
            val priceWithVAT = calculateVAT(product.price)
            val newProduct = Product(product.name, priceWithVAT)
            productsWithVAT.add(newProduct)
        }

        return productsWithVAT

    }


}