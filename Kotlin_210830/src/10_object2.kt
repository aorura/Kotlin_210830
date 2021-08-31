// 10_object2.kt
package ex10_2

// 2. companion object
//   : 동반 객체
//  => 코틀린은 static 필드 / 메소드 키워드가 존재하지 않습니다.
/*
class User {
    private static final String TAG = "User";

    static {
        System.out.println("Hello, Kotlin");
    }

    public static void printTag() {
        System.out.println(TAG);
    }
}
*/

// const val
//       val
//  - 상수
//  1) 런타임 상수     : 변수(메모리 공간)인데, 문법적으로 변경을 허용하지 않는다.
//      - val
//  2) 컴파일 타임 상수 : 메모리 공간을 차지하지 않는 상수
//      - const val
//      : 문자열 상수 / 정수 / 실수의 상수에 대해서만 사용할 수 있습니다.
class User {
    companion object {
        private const val TAG = "User"

        fun printTag() {
            println(TAG)
        }
    }
}

fun main() {
   User.printTag()
}



