package actividadesXML.act4

import java.math.RoundingMode


class Operations {

    companion object {
        const val VAT = 21
    }


    private fun calculateVAT(productPrice: Float): Float {
        val vatMultiplier = 1.0f + (VAT / 100.0f)
        return productPrice * (vatMultiplier)
    }


    private fun round(number: Float, decimals: Int): Float {
        val amountOfDecimals = if (decimals <= 0) {
            1
        } else {
            decimals
        }

        return number.toBigDecimal().setScale(amountOfDecimals, RoundingMode.HALF_UP).toFloat()
    }


    fun calculateProductsWithVAT(products: List<Product>): List<Product> {
        val productsWithVAT = mutableListOf<Product>()

        for (product in products) {
            val priceWithVAT = round(calculateVAT(product.price), 2)
            val newProduct = Product(product.name, priceWithVAT)
            productsWithVAT.add(newProduct)
        }

        return productsWithVAT

    }


}