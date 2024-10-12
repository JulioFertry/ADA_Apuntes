package actividadesXML.ejerCRUD.model

data class Empleado(
    val id: String,
    val apellido: String,
    val departamento: String,
    val salario: Double
) {

    override fun toString(): String {
        return "ID: '$id', apellido='$apellido', dpto='$departamento', salario=$salario)"
    }

}
