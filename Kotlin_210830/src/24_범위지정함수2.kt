// 24_범위지정함수2.kt
package ex24_2

import ex24.apply

data class User(val name: String, val age: Int)
class SQLBuilder {
    fun append(query: String) {}
}

class Database {
    fun create(query: SQLBuilder): Boolean {
        return true
    }
}

fun main() {
    val user = User("Tom", 42)
    //----
    /*
    val database = Database()
    val query = SQLBuilder()
    query.append("INSERT INTO user(name, age) VALUES ")
    query.append("(${user.name}, ${user.age})")
    val result = database.create(query)
    println(result)
    */
    //----
    val result2 = SQLBuilder()
        .apply {
            append("INSERT INTO user(name, age) VALUES ")
            append("(${user.name}, ${user.age})")
        }.also {
            println("Query logging...${it}")
            // it.append("xxx") // !!!!!!!
        }.run {
            val database = Database()
            database.create(this)
        }
    println(result2)
}