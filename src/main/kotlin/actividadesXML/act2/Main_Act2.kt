package actividadesXML.act2

import org.w3c.dom.Element
import org.w3c.dom.Node
import java.nio.file.Path
import javax.xml.parsers.DocumentBuilderFactory


fun getEmployees(employeeFile: Path): MutableList<Employee> {
    val employeeReturnableList = mutableListOf<Employee>()

    val docBuilderFactory = DocumentBuilderFactory.newInstance()
    val docBuilder = docBuilderFactory.newDocumentBuilder()
    val employeeDoc = docBuilder.parse(employeeFile.toFile())

    val root = employeeDoc.documentElement
    root.normalize()

    val employeeList = root.getElementsByTagName("empleado")

    for (i in 0..<employeeList.length) {

        val node = employeeList.item(i)
        if (node.nodeType == Node.ELEMENT_NODE) {
            val elementNode = node as Element

            val idAttribute = elementNode.getAttribute("id").toInt()
            val surnameElement = elementNode.getElementsByTagName("apellido")
            val salaryElement = elementNode.getElementsByTagName("salario")
            val deptElement = elementNode.getElementsByTagName("dep")

            val textContentSurname = surnameElement.item(0).textContent
            val textContentSalary = salaryElement.item(0).textContent.toFloat()
            val textContentDept = deptElement.item(0).textContent.toInt()

            val employee = Employee(idAttribute, textContentSurname, textContentDept, textContentSalary)
            employeeReturnableList.add(employee)
        }
    }

    return employeeReturnableList
}

fun main() {
    val employeeFile = Path.of("src")
        .resolve("main")
        .resolve("resources")
        .resolve("archivosXML")
        .resolve("empleados.xml")

    val employees = getEmployees(employeeFile)

    for (employee in employees) {
        println(employee)
    }

}