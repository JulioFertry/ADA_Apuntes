package actividadesXML.act2

data class Employee(
    val id: Int,
    val surname: String,
    val department: Int,
    val salary: Float
) {

    override fun toString(): String {
        return "$id: (surname='$surname', dept=$department, salary=$salary$)"
    }

}
