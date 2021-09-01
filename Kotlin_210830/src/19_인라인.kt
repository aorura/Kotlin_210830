// 19_인라인.kt
package ex19

class IncThread : Thread() {
    companion object {
        var n = 0
    }

    override fun run() {
        // for (int i = 0 ; i < 1000000; ++i)
        for (i in 1..1_000_000) {
            n += 1
        }
    }
}

fun main() {
    val t1 = IncThread()
    val t2 = IncThread()

    t1.start()
    t2.start()

    t1.join()
    t2.join()

    println(IncThread.n)
}