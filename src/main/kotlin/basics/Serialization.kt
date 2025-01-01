package basics

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString

@Serializable
data class Person(val name: String, val age: Int)

fun main() {
    val person = Person("Omar", 20)
    val jsonString = Json.encodeToString(person)
    println(jsonString) // Outputs: {"name":"Omar","age":20}

    val person2 = Json.decodeFromString<Person>(jsonString)
    println(person2)   // Outputs: Person(name=Omar, age=20)
}