// 11_위임4.kt
package ex11_4

import java.util.concurrent.TimeUnit
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

// 코틀린이 기본적으로 제공하는 프로퍼티 위임 객체


class Image {
    init {
        println("Image 객체 생성 시작")
        TimeUnit.SECONDS.sleep(2)  // 2초
        println("Image 객체 생성 완료")
    }

    fun draw() {
        println("Image draw")
    }
}

// 1. lazy 객체
//   => 지연 초기화
//      객체를 바로 생성하는 것이 아니라, 처음으로 접근되는 시점에 생성한다.
//      "스레드 안전합니다."
//   => getter 에 대해서만 지원합니다.
//      val 에서만 사용할 수 있습니다.
class User {
    // val image: Image = Image()
    val image: Image by lazy {
        Image()
    }

    // 블록의 결과는 마지막 표현식에 의해 결정됩니다.
    // var image2: Image? = null
    lateinit var image2: Image

    // * lateinit var
    //  : 주의해서 사용해야 합니다.
    // - 초기화되지 않은 프로퍼티를 사용할 경우, 예외가 발생합니다.
    //   UninitializedPropertyAccessException
    // - 참조 타입에 대해서만 사용 가능합니다.
    //   : Int, Double, Long, Float ...
    fun drawImage() {
        image.draw()
        image2.draw()
    }
}

// 2. 프로퍼티의 변경을 관찰할 수 있습니다.
//    Delegates.observable
//    프로퍼티의 값의 변경을 조건을 체크해서 변경하고 싶다.
//    Delegates.vetoable

class Data {
    fun isValid(): Boolean {
        return true
    }
}


class Person(var firstName: String, var lastName: String) {
    // var fullName: String = "$firstName $lastName"

    var data: Data by Delegates.vetoable(Data())
    { _: KProperty<*>, _: Data, new: Data ->
        new.isValid()
    }

    // fullName이 변경될 때마다, firstName과 lastName도 변경되도록 하는 로직
    var fullName: String by Delegates.observable("$firstName $lastName")
    { prop: KProperty<*>, old: String, new: String ->
        val arr = new.split(" ")
        firstName = arr[0]
        lastName = arr[1]
    }

    var age: Int by Delegates.vetoable(1)
    { prop: KProperty<*>, old: Int, new: Int ->
        new > 0
    }
}

fun main() {
    val person = Person("Gildong", "Hong")
    person.age = 5
    println(person.age)
    person.age = -5
    println(person.age)

    person.fullName = "Soonshin Lee"
    println(person.firstName)
    println(person.lastName)

    println("User 객체 생성 시작")
    val user = User()
    user.image2 = Image()

    println("User 객체 생성 완료")
    user.drawImage() // !!!!!!
    user.drawImage()
}