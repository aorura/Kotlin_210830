// 19_인라인3.kt
package ex19

// Inline class

// 'inline' modifier is deprecated. Use 'value' instead
// Value classes without @JvmInline annotation are not supported yet
@JvmInline
value class Password(val s: String)

@JvmInline
value class Address(val s: String)

@JvmInline
value class Name(val s: String)

class User(
    val name: Name,
    val address: Address,
    val password: Password,
)

fun main() {
    val user = User(Name("aaa"), Address("bbb"), Password("ccc"))
}