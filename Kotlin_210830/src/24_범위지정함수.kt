// 24_범위지정함수.kt
package ex24

// 1. apply
// 2. with
// 3. let
// 4. also
// 5. run

// 각각의 목적에 맞게 사용해야 합니다.
// - apply
//  : 객체의 초기화 과정에서 사용하는 범위 지정 함수 입니다.

inline fun <T> T.apply(block: T.() -> Unit): T {
    block() // block(this) // this.block()
    return this
}
/*
fun main() {
    val sb = StringBuilder()
    for (letter in 'A'..'Z')
        sb.append(letter)
    val result = sb.toString()
    println(result)

    val result2 = StringBuilder().apply {
        for (letter in 'A'..'Z')
            append(letter)
    }.toString()
    println(result2)

    val result3 = buildString {
        for (letter in 'A'..'Z')
            append(letter)
    }
    println(result3)
}
*/

class Home {
    val user: User = User()
}

class User {
    val name: String = "Tom"
    val age: Int = 42
}

// 2. with
// : null이 아닌 수신 객체이고, 결과가 필요하지 않은 경우에 with를 사용합니다.
inline fun <T, R> with(receiver: T, block: T.() -> R): R {
    return receiver.block()
}

/*
fun main() {
    val home = Home()
    println(home.user.name)
    println(home.user.age)

    with(home.user) {
        println(name)
        println(age)
    }
}
*/

// 3. let
// => nullable 타입에 대해서, null이 아닐 때 수행해야 하는 작업을 지정할 때 사용합니다.
//    nullable 객체를 다른 타입의 nullable 객체로 변환할때도 사용됩니다.
//    단일 지역변수의 범위를 제한하는 용도로 사용할 수 있습니다.
// => let을 잘못 사용하는 경우, 코드의 가독성이 떨어집니다.

fun sendMail(email: String): Boolean {
    println("Send mail to $email")
    return true
}

fun getEmail(): String? {
    return "hello@gmail.com"
}

inline fun <T, R> T.let(block: (T) -> R): R {
    return block(this)
}

fun main() {
    val email: String? = null
    val user: User? = null
    email?.let {
        user?.let {
        }
    }
    if (email != null && user != null) {
    }

    // 단일 지역변수의 범위를 제한하는 용도로 사용할 수 있습니다.
    getEmail()?.let { email ->
        sendMail(email)
    }
    
    // val email: String? = getEmail()
    val isSendEmail2: Boolean = if (email != null) {
        sendMail(email)
    } else {
        false
    }

    val isSendEmail: Boolean = email?.let {
        sendMail(it)
    } ?: false
}




