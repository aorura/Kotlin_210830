// 21_메모이제이션2.kt
package ex21_2

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.util.*

class User(
    val name: String = "Tom",
    val age: Int = 42,
    val grade: String = "admin",
    val address: String = "Seoul",
    val createdAt: Date = Date()
)

// User -> JSON
// : Gson(Google JSON Serialization / Deserialization)
fun main() {
    // val gson = Gson()
    val gson = GsonBuilder()
        .setPrettyPrinting()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create()

    val user = User()
    val json = gson.toJson(user)

    println(json)
}










