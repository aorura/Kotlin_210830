// 21_메모이제이션2.kt
package ex21_2

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder

data class User(
    val name: String = "Tom",
    val age: Int = 42,
    val grade: String = "admin",
    val address: String = "Seoul",
    // val createdAt: Date = Date()
) {
    fun toJson(): String {
        println("User::toJson")
        val gson = GsonBuilder()
            .setPrettyPrinting()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()
        return gson.toJson(this)
    }
}

// 기존 함수에 메모이제이션의 기능을 추가된 함수를 만드는 함수를 만듭니다.
//  (User) -> String
fun ((User) -> String).memoized(): (User) -> String {
    val cache = mutableMapOf<User, String>()
    return { k ->
        cache.getOrPut(k) {
            this(k)
        }
    }
}

// User -> JSON
// : Gson(Google JSON Serialization / Deserialization)
fun main() {
    val user1 = User()
    val user2 = User()
    println(user1.hashCode())
    println(user2.hashCode())

    val toJson = User::toJson.memoized()
    println(toJson(user1))
    println(toJson(user2))
    println(toJson(user1))
    println(toJson(user2))

    /*
    val user = User()
    val json2 = user.toJson()
    println(json2)
    println(user.toJson())
    println(user.toJson())
    println(user.toJson())
    println(user.toJson())
    */


    // val gson = Gson()
    /*
    val gson = GsonBuilder()
        .setPrettyPrinting()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create()
    val json = gson.toJson(user)
    println(json)
    */
}










