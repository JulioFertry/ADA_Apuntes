package actividadesXML.act3

import java.util.Date

data class Report(
    val title: String,
    val link: String,
    val guid: String,
    val pubDate: Date,
    val description: String
)
