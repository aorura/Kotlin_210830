// 23_컬렉션2.kt
package ex23_2

// Kotlin의 Collection은 기본적으로 제공하는
// 다양한 연산이 있습니다.
fun main() {
    val list = listOf(
        "Seoul", "Busan", "Daegu", "Yongin", "Suwon"
    )

    // 1. forEach
    // list.forEach { e ->
    //    println(e)
    // }
    // list.forEach(::println)

    // 2. map
    //  - transform
    //  List<T>  -> map -> List<U>
    //  List<String> -> map -> List<Int>
    val result = list.map { e ->
        e.length
    }
    println(result)
    // val result2 = list.map { e ->
    //    e.lowercase()   // lowercase(e)
    // }
    val result2 = list.map(String::lowercase)
    println(result2)

    // 3. filter
    // : 조건에 만족하지 않은 요소를 제거한다.
    val result3 = list.filter { e ->
        e.startsWith("S")
    }
    println(result3)

    // 4. map + filter
    /*
    val result4: List<String?> = list
            // List<String> ->  map  -> List<String?>
        .map { e ->
            if (e.startsWith("S"))
                e.lowercase()
            else
                null
        }
        .filter { it != null }
    */
    /*
    val result4: List<String> = list
        // List<String> ->  map  -> List<String?>
        .map { e ->
            if (e.startsWith("S"))
                e.lowercase()
            else
                null
        }
        .filterNotNull()  // List<String?> -> filterNotNull -> List<String>
     */
    // 변환을 수행하면서, null로 지정하면, filter가 수행됩니다.
    val result4: List<String> = list
        // List<T> ->  mapNotNull  -> List<U>
        .mapNotNull { e ->
            if (e.startsWith("S"))
                e.lowercase()
            else
                null
        }
    println(result4)

    // 5. flatMap
    //   List<T> -> map         -> List<List<U>>
    //   List<T> -> flatMap     -> List<U>
    val list2 = listOf(
        "서울 특별시",
        "부산 광역시",
        "인천 광역시",
    )
    // List<String> -> map      -> List<List<String>>
    // List<String> -> flatMap  -> List<String>
    val result5 = list2.flatMap { city ->
        city.split(" ")      // List<String>
    }
    println(result5)

    // 6. take / drop
    val list3 = listOf(1, 2, 3, 1, 1, 1)
    // take: 원하는 크기의 데이터를 추출할 때 사용합니다.
    // drop: 원하는 크기만큼 제거하고 추출합니다.
    println(list3.take(5))
    println(list3.drop(2))

    // 조건을 사용하는 것도 가능합니다.
    println(list3.dropWhile {
        it < 2
    })

    // 7. distinct: 중복된 요소를 제거합니다.
    println(list3.distinct())
    println(list3.distinctBy {
        it % 2 == 0
    })

    // 8. firstOrNull / lastOrNull
    //  => 예외를 통해서 오류를 처리하는 것도 가능하지만,
    //     단일 실패 원인의 경우는 Nullable를 통해 처리하는 경우가 많습니다.
    // val list4 = listOf(1)
    val list4 = emptyList<Int>()
    // println(list4[0])
    val result6: Int? = list4.firstOrNull()
    if (result6 != null) {
        println(result6)
    }

    val list5 = listOf(
        User("Tom", 11),
        User("Alice", 12),
        User("Bob", 13),
        User("David", 20),
        User("Mike", 30),
        User("Tim", 40),
    )
    // 나이대로 분류하고 싶습니다.
    // 9. groupBy
    // List<User> -> groupBy -> Map<String, List<User>>
    val result7 = list5.groupBy { user ->
        when (user.age) {
            in 0..9 -> "어린이"
            in 10..19 -> "청소년"
            in 20..29 -> "청년"
            else -> "어른"
        }
    }
    println(result7)

    // 10. zip
    val countries = listOf("Korea", "United States", "China")
    val codes = listOf("KR", "US", "CN")

    val result8 = countries.zip(codes)
    result8.forEach { pair ->
        println("${pair.first}(${pair.second})")
    }
    result8.forEach { (country, code) ->
        println("${country}(${code})")
    }

    println(result8)

    // 위의 API를 Sequence API 라고 합니다.
    // => Java 8 에서는 Stream API를 제공합니다.
    // => Sequence API는 모든 데이터를 메모리에 올려서 일괄적으로 연산을 수행합니다.
    //    "Stream API"는 순차적으로 연산을 수행합니다.
    //     : 병렬 처리가 쉽게 가능합니다.
    // => Minimum SDK 24 이상 / Gradle 4 이상 되어야 합니다.
    //
    list
        // .stream()
        .parallelStream()
        .map { it.lowercase() }
        .forEach(::println)
}

data class User(val name: String, val age: Int)








