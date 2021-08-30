// 05_접근지정자.kt
package ex5

// 정보 은닉
//  private - package - protected - public
// 문제점
// 1. 자바의 protected는 같은 패키지에서 바로 접근이 가능합니다.
//   => 코틀린에서 protected는 반드시 자식 클래스를 통해 접근할 수 있습니다.
// 2. 다른 모듈이라도, 같은 패키지 이름을 가지고 있다면,
//    패키지 레벨의 모든 것에 접근이 가능합니다.

// IntelliJ / Android Studio
//  Project
//   - app module : com.lge
//  외부 라이브러리
//   - module: com.lge.A
//   - module: com.lge.B
//   - module: com.lge.C

// Kotlin
//  private - internal - protected - public
//  - internal: 같은 모듈에서만 접근이 가능합니다.
//   > 다른 모듈이라면, 같은 패키지 이름을 가지고 있다고 하더라도
//     컴파일시 다른 이름으로 변경해버립니다.

//----
// class / interface / enum
//  Java: public / package

// class / 전역 fun, var, val / interface / enum
//   public: 외부 모듈에서도 접근이 가능합니다.
// internal: 같은 모듈에서만 접근이 가능합니다.
//  private: 같은 파일에서만 접근이 가능합니다.

private class User(protected val name: String = "Bob")
private fun foo() {
}
private val n = 40

fun main() {
    val user = User()
    // println(user.name)
    // Kotlin: Cannot access 'name': it is protected in 'User'
}
