// 01_시작.kt
// 1. 코틀린 언어 특징
//  - 간결함
//   : 자바에서 발생하는 보일러플레이트(상용구)를 제거합니다.
//  - 안전함
//   : Null을 안전하게 다루는 방법을 언어적으로 제공합니다.
//    => Nullable / Optional
//    모든 타입은 Nullable 타입과 Nullable 아닌 타입으로 정리할 수 있습니다.
//    - 일반적인 타입은 null을 사용할 수 없습니다.    -> String
//    - Nullable 타입만 null을 사용할 수 있습니다.  -> String?
//  - 상호운용성
//   : 별도의 설정 없이, 자바의 모든 코드를 코틀린에서 이용할 수 있고,
//     코틀린의 코드를 자바에서 이용할 수 있습니다.
//     => ObjC - Bridge - Swift
//     => 코틀린은 별도의 브릿지의 개념을 사용할 필요가 없습니다.

/*
// Hello.java
public class Hello {
    public static void main(String[] args) {
        System.out.println("Hello, Java");
    }
}
*/
// $ javac Hello.java
//  -> Hello.class
// $ java Hello
// $ javap -c Hello
/*
public class Hello {
  public Hello();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
       3: ldc           #3                  // String Hello, Java
       5: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
       8: return
}
*/

/*
fun main() {
    println("Hello, Kotlin")
}
*/
// $ kotlinc Hello.kt
//  -> HelloKt.class
// $ java HelloKt
/*
public final class HelloKt {
  public static final void main();
    Code:
       0: ldc           #8                  // String Hello, Kotlin
       2: astore_0
       3: iconst_0
       4: istore_1
       5: getstatic     #14                 // Field java/lang/System.out:Ljava/io/PrintStream;
       8: aload_0
       9: invokevirtual #20                 // Method java/io/PrintStream.println:(Ljava/lang/Object;)V
      12: return

  public static void main(java.lang.String[]);
    Code:
       0: invokestatic  #23                 // Method main:()V
       3: return
}
*/

fun main(args: Array<String>) {
    println("Hello, Kotlin")
}
/*
public final class HelloKt {
  public static final void main(java.lang.String[]);
    Code:
       0: aload_0
       1: ldc           #9                  // String args
       3: invokestatic  #15                 // Method kotlin/jvm/internal/Intrinsics.checkNotNullParameter:(Ljava/lang/Object;Ljava/lang/String;)V
       6: ldc           #17                 // String Hello, Kotlin
       8: astore_1
       9: iconst_0
      10: istore_2
      11: getstatic     #23                 // Field java/lang/System.out:Ljava/io/PrintStream;
      14: aload_1
      15: invokevirtual #29                 // Method java/io/PrintStream.println:(Ljava/lang/Object;)V
      18: return
}
*/
// 1) kotlin 런타임을 이용합니다.
//   => java 런타임을 이용하지만, 코틀린의 코드를 동작하는데 필요한 클래스에 대한 로드가 수행됩니다.
//   $ kotlin HelloKt

// 2) kotlinc 컴파일러를 통해 컴파일을 할때, 코틀린에 필요한 라이브러리를 같이 패키징합니다.
//   $ kotlinc Hello.kt -include-runtime -d Hello.jar
//   $ java -jar Hello.jar






