@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson5.task1


/**
 * Пример
 *
 * Для заданного списка покупок `shoppingList` посчитать его общую стоимость
 * на основе цен из `costs`. В случае неизвестной цены считать, что товар
 * игнорируется.
 */
fun shoppingListCost(
        shoppingList: List<String>,
        costs: Map<String, Double>): Double {
    var totalCost = 0.0

    for (item in shoppingList) {
        val itemCost = costs[item]
        if (itemCost != null) {
            totalCost += itemCost
        }
    }

    return totalCost
}

/**
 * Пример
 *
 * Для набора "имя"-"номер телефона" `phoneBook` оставить только такие пары,
 * для которых телефон начинается с заданного кода страны `countryCode`
 */
fun filterByCountryCode(
        phoneBook: MutableMap<String, String>,
        countryCode: String) {
    val namesToRemove = mutableListOf<String>()

    for ((name, phone) in phoneBook) {
        if (!phone.startsWith(countryCode)) {
            namesToRemove.add(name)
        }
    }

    for (name in namesToRemove) {
        phoneBook.remove(name)
    }
}

/**
 * Пример
 *
 * Для заданного текста `text` убрать заданные слова-паразиты `fillerWords`
 * и вернуть отфильтрованный текст
 */
fun removeFillerWords(
        text: List<String>,
        vararg fillerWords: String): List<String> {
    val fillerWordSet = setOf(*fillerWords)

    val res = mutableListOf<String>()
    for (word in text) {
        if (word !in fillerWordSet) {
            res += word
        }
    }
    return res
}

/**
 * Пример
 *
 * Для заданного текста `text` построить множество встречающихся в нем слов
 */
fun buildWordSet(text: List<String>): MutableSet<String> {
    val res = mutableSetOf<String>()
    for (word in text) res.add(word)
    return res
}

/**
 * Средняя
 *
 * Объединить два ассоциативных массива `mapA` и `mapB` с парами
 * "имя"-"номер телефона" в итоговый ассоциативный массив, склеивая
 * значения для повторяющихся ключей через запятую.
 * В случае повторяющихся *ключей* значение из mapA должно быть
 * перед значением из mapB.
 *
 * Повторяющиеся *значения* следует добавлять только один раз.
 *
 * Например:
 *   mergePhoneBooks(
 *     mapOf("Emergency" to "112", "Police" to "02"),
 *     mapOf("Emergency" to "911", "Police" to "02")
 *   ) -> mapOf("Emergency" to "112, 911", "Police" to "02")
 */
fun mergePhoneBooks(mapA: Map<String, String>, mapB: Map<String, String>): Map<String, String> {
    val map = (mapB + mapA).toMutableMap()       // соединяем массивы, где мапА затирает повторяющие ключи мапБ
    val mapC = mutableMapOf<String, String>()       // создаем промежуточный массив,к уда записываем новые повторяющиеся значения
    for ((key, value) in map) {              // цикл для формирования промежуточного массива мапС
        if (value != mapB[key] && mapB.contains(key)) {     // условия, где значение по ключу не равно мжде собой и ключ входит в мапБ
            mapC[key] = "$value, ${mapB[key]}"            // запись промежуточного массива
        }
    }
    map += mapC
    return map
}

/**
 * Простая
 *
 * По заданному ассоциативному массиву "студент"-"оценка за экзамен" построить
 * обратный массив "оценка за экзамен"-"список студентов с этой оценкой".
 *
 * Например:
 *   buildGrades(mapOf("Марат" to 3, "Семён" to 5, "Михаил" to 5))
 *     -> mapOf(5 to listOf("Семён", "Михаил"), 3 to listOf("Марат"))
 */
fun buildGrades(grades: Map<String, Int>): Map<Int, List<String>> {
    val map1 = mutableMapOf<Int, List<String>>() // МАП для ответа
    val map2 = mutableMapOf<Int, String>()      // МАП с измененным ключами и последними значениями (старые ключи)
    for ((name, rating) in grades) {                // цикл для изменения ключей в МАП
        map2.put(rating, name)                          // присвоение старого значение в ключ и наоборот (Семен ту 5 -> 5 ту Семен)
    }
    for ((key, value) in map2) {            // основной цикл для записи в нувую МАП всех старфх ключей (Имена)
        val a = mutableListOf<String>()     // переменная куда будем складывать старые ключи (имена)
        for ((name, rating) in grades) {           // цикл для выбора и записи старых ключей (имена)
            if (key == rating) {                    // условие выбора
                a += name               // запись в список старых ключей (имена)
            }
        }
        map1[key] = a               // при своение нового значения и ключа в Нову МАП для окончательного ответа
    }
    return map1
}

/**
 * Простая
 *
 * Определить, входит ли ассоциативный массив a в ассоциативный массив b;
 * это выполняется, если все ключи из a содержатся в b с такими же значениями.
 *
 * Например:
 *   containsIn(mapOf("a" to "z"), mapOf("a" to "z", "b" to "sweet")) -> true
 *   containsIn(mapOf("a" to "z"), mapOf("a" to "zee", "b" to "sweet")) -> false
 */
fun containsIn(a: Map<String, String>, b: Map<String, String>): Boolean {
    var c = false                           // переменная правда ложь, с изначальным значением ложь
    for ((key, value) in a) {    // цикл по массиву а для выявления одинаковых значений для клбчей из а
        for ((key1, value1) in b) {
            if (key == key1 && value == value1) {               // условие при котором сравнивается значение из а со значением из б
                c = true                              // правда если сходится
            }
        }
    }
    return c
}

/**
 * Средняя
 *
 * Для заданного списка пар "акция"-"стоимость" вернуть ассоциативный массив,
 * содержащий для каждой акции ее усредненную стоимость.
 *
 * Например:
 *   averageStockPrice(listOf("MSFT" to 100.0, "MSFT" to 200.0, "NFLX" to 40.0))
 *     -> mapOf("MSFT" to 150.0, "NFLX" to 40.0)
 */
fun averageStockPrice(stockPrices: List<Pair<String, Double>>): Map<String, Double> {
    val list = stockPrices.map { it.first }.toSet() // Переменная из кей из пары, преобразованый в сет для удаления повторяющих элементов
    var avAmount = 0.0                      // суда будем считать среднюю сумму
    val res = (mutableMapOf<String, Double>() + stockPrices).toMutableMap() // скалдываем в массив элементы пары удаляя паралельно повторяющиеся ключи и затираа их значения
    val sum = mutableListOf<Double>()    // суматор (список) для посторяющихся значений
    for (i in list) {                        // цикл для вычисления средней суммы для оставщихся уникальных ключей в списке
        for ((key, value) in stockPrices) { // цикл по вычислению повторяющихся ключей из оригинального списка
            if (i == key) {                      // условие для посторяющего ключа
                sum += value                    // сумирование значений ключа
                avAmount = sum.average()    // вычисление средней суммы значений
            }
        }
        res[i] = avAmount           // запись значение к соответствюшему ключу в МАП
        sum.clear()                 // обнуление списка
    }
    return res
}

/**
 * Средняя
 *
 * Входными данными является ассоциативный массив
 * "название товара"-"пара (тип товара, цена товара)"
 * и тип интересующего нас товара.
 * Необходимо вернуть название товара заданного типа с минимальной стоимостью
 * или null в случае, если товаров такого типа нет.
 *
 * Например:
 *   findCheapestStuff(
 *     mapOf("Мария" to ("печенье" to 20.0), "Орео" to ("печенье" to 100.0)),
 *     "печенье"
 *   ) -> "Мария"
 */
fun findCheapestStuff(stuff: Map<String, Pair<String, Double>>, kind: String): String? {
    var res: String? = null                                       //переменной задаем значение ноль, если вдруг не будет подходящих значений
    var a = 10000.00                                              // Переменная с которой будем сравнивать ценну товара, и записывать прошлое ее значение
    for ((name, value) in stuff) {       // цикл по массиву для определения самой дешевого продукта для kind
        if (value.first == kind) {                                 // условия если значение пары (первое (ключа)) равно kind
            if (value.second < a) {                                // и если его значение меньше прошлого значения того же продукта
                res = name                                          // то в результат записываем наименование (name)
            }
        }
        a = value.second                                    // записываем прошлую ценну на продукт, который равный kind
    }
    return res
}


/**
 * Сложная
 *
 * Для заданного ассоциативного массива знакомых через одно рукопожатие `friends`
 * необходимо построить его максимальное расширение по рукопожатиям, то есть,
 * для каждого человека найти всех людей, с которыми он знаком через любое
 * количество рукопожатий.
 * Считать, что все имена людей являются уникальными, а также что рукопожатия
 * являются направленными, то есть, если Марат знает Свету, то это не означает,
 * что Света знает Марата.
 *
 * Например:
 *   propagateHandshakes(
 *     mapOf(
 *       "Marat" to setOf("Mikhail", "Sveta"),
 *       "Sveta" to setOf("Marat"),
 *       "Mikhail" to setOf("Sveta")
 *     )
 *   ) -> mapOf(
 *          "Marat" to setOf("Mikhail", "Sveta"),
 *          "Sveta" to setOf("Marat", "Mikhail"),
 *          "Mikhail" to setOf("Sveta", "Marat")
 *        )
 */
fun propagateHandshakes(friends: Map<String, Set<String>>): Map<String, Set<String>> {
    val res = mutableMapOf<String, Set<String>>()
    val allFriends = mutableSetOf<String>()
    for ((name, listSet) in friends) {
        val s = mutableSetOf<String>()
        s.addAll(listSet)
        allFriends.addAll(listSet)
        for ((name1, listSet1) in friends - name) {
            if (s.contains(name1)) {
                s.addAll(listSet1)
            }
        }
        s.remove(name)
        res.put(name, s)
    }
    for (i in allFriends) {
        if (!res.contains(i))
            res[i] = setOf()
    }
    return res
}
fun main(args: Array<String>) {
    print(propagateHandshakes(mapOf("Marat" to setOf("Mikhail", "Sveta"),"Sveta" to setOf("Marat", "Mikhail"),
            "Yuriy" to setOf(), "Mikhail" to setOf("Sveta", "Marat"))))
}
/**
 * Простая
 *
 * Удалить из изменяемого ассоциативного массива все записи,
 * которые встречаются в заданном ассоциативном массиве.
 * Записи считать одинаковыми, если и ключи, и значения совпадают.
 *
 * ВАЖНО: необходимо изменить переданный в качестве аргумента
 *        изменяемый ассоциативный массив
 *
 * Например:
 *   subtractOf(a = mutableMapOf("a" to "z"), mapOf("a" to "z"))
 *     -> a changes to mutableMapOf() aka becomes empty
 */
fun subtractOf(a: MutableMap<String, String>, b: Map<String, String>): Unit {
    for ((key, value) in b) {             // Цикл по Массииву б
        if (a.containsKey(key) && a.containsValue(value)) {  // услвоие где сравниваем наличие ключа и значения массива б в массиве а
            a.remove(key, value)                // если ключи есть удаляем из массива а
        }
    }                                       // UNIT не возвращает ничего (без return)
}

/**
 * Простая
 *
 * Для двух списков людей найти людей, встречающихся в обоих списках.
 * В выходном списке не должно быть повторяюихся элементов,
 * т. е. whoAreInBoth(listOf("Марат", "Семён, "Марат"), listOf("Марат", "Марат")) == listOf("Марат")
 */
fun whoAreInBoth(a: List<String>, b: List<String>): List<String> {
    val res = mutableListOf<String>() // переменная для записи ответа
    for (i in a.toSet()) {                  // цикл для определение 1 слова из списка
        for (j in b.toSet()) {           // цикл сравнение 1 слова со словами из второго списка
            if (i == j) {                // условие если слова равны
                res += i                // записать слово в итоговый список
            }
        }
    }
    return res
}

/**
 * Средняя
 *
 * Для заданного набора символов определить, можно ли составить из него
 * указанное слово (регистр символов игнорируется)
 *
 * Например:
 *   canBuildFrom(listOf('a', 'b', 'o'), "baobab") -> true
 */
fun canBuildFrom(chars: List<Char>, word: String): Boolean {
    val listSet = word.toLowerCase().toSet() //список из символов слова в котором изменяем регистр и делаем множество символов
    var bool = true                         // переменная правда ложь
    val low = chars.map { it.toLowerCase() }
    for (i in listSet) {            // цикл для поиска символов из которых можно составлять слова
        if (!low.contains(i)) {       // условие нахождения символа в списке символов
            bool = false            // если символа нет, то ложь
        }
    }
    return bool
}

/**
 * Средняя
 *
 * Найти в заданном списке повторяющиеся элементы и вернуть
 * ассоциативный массив с информацией о числе повторений
 * для каждого повторяющегося элемента.
 * Если элемент встречается только один раз, включать его в результат
 * не следует.
 *
 * Например:
 *   extractRepeats(listOf("a", "b", "a")) -> mapOf("a" to 2)
 */
fun extractRepeats(list: List<String>): Map<String, Int> {
    val map = mutableMapOf<String, Int>() // создаем переменную МАП (Строка и число)
    val s = list.toSet()            // преобразовываем список во вножемтво (кдаляем повторяюищиеся элементы)
    var cnt = 0                         // счетчик для подсчета количества повторяющихся элементов
    for (i in s) {              // цикл для определения слова для сравнения с остальными
        if (list.contains(i)) {         // условие если в списке есть выбранное слово, то
            for (j in list) {      // цикл для определения количества повторений
                if (j == i) cnt++           // увеличиваем счетчик на каждоне повторение
                if (cnt > 1) map.put(i, cnt) // если повторений больше 1, то записываем в массив значение
            }
            cnt = 0                 // обнуляем счетчик для следующей буквы
        }
    }
    return map
}

/**
 * Средняя
 *
 * Для заданного списка слов определить, содержит ли он анаграммы
 * (два слова являются анаграммами, если одно можно составить из второго)
 *
 * Например:
 *   hasAnagrams(listOf("тор", "свет", "рот")) -> true
 */
fun hasAnagrams(words: List<String>): Boolean {
    var res = false                                 // переменная куда будет записываться ответ изначально ложь
    var a = mutableListOf<Char>()   // переменная список симолов
    var b = mutableListOf<Char>()   // перенная список символов
    for (i in 0 until words.size) {              // цикл для определения первого слова по индексу списка
        a = words[i].toMutableList()            //занесение в список символов
        for (j in words - words[i]) {       // цикл для сравнения первого слова с остальными в списке за минусом того слова которое выбрали
            b = j.toMutableList()           // определние второго слова и занесение его в список символов
            if (a.sorted() == b.sorted()) {  // условие равенства отсортированных по порядку слов в списке символов
                res = true                  // если равны то правда
                return res                  // прекращаем цикл
            }
        }
    }
    return res
}

/**
 * Сложная
 *
 * Для заданного списка неотрицательных чисел и числа определить,
 * есть ли в списке пара чисел таких, что их сумма равна заданному числу.
 * Если да, верните их индексы в виде Pair<Int, Int>;
 * если нет, верните пару Pair(-1, -1).
 *
 * Индексы в результате должны следовать в порядке (меньший, больший).
 *
 * Постарайтесь сделать ваше решение как можно более эффективным,
 * используя то, что вы узнали в данном уроке.
 *
 * Например:
 *   findSumOfTwo(listOf(1, 2, 3), 4) -> Pair(0, 2)
 *   findSumOfTwo(listOf(1, 2, 3), 6) -> Pair(-1, -1)
 */
fun findSumOfTwo(list: List<Int>, number: Int): Pair<Int, Int> {
    var resPair: Pair<Int, Int> = -1 to -1          // пара в которую будет записанн результат
    for (i in list){                    // цикл для цифры для первой пары
        for (j in list - i){            //  цикл для цифры для воторой пары
            if (i + j == number){           // если обе цифры равны, то записывам в ответ
                resPair = list.indexOf(i) to list.indexOf(j)    // запись пар в ответ
                return resPair                                  // останавливаем процесс и возвращаем ответ
            }
        }
    }
    return resPair
}


/**
 * Очень сложная
 *
 * Входными данными является ассоциативный массив
 * "название сокровища"-"пара (вес сокровища, цена сокровища)"
 * и вместимость вашего рюкзака.
 * Необходимо вернуть множество сокровищ с максимальной суммарной стоимостью,
 * которые вы можете унести в рюкзаке.
 *
 * Например:
 *   bagPacking(
 *     mapOf("Кубок" to (500 to 2000), "Слиток" to (1000 to 5000)),
 *     850
 *   ) -> setOf("Кубок")
 *   bagPacking(
 *     mapOf("Кубок" to (500 to 2000), "Слиток" to (1000 to 5000)),
 *     450
 *   ) -> emptySet()
 */

fun bagPacking(treasures: Map<String, Pair<Int, Int>>, capacity: Int): Set<String> = TODO()

// {
//    val mapNew: MutableMap<String, Pair<Int, Int>> = mutableMapOf()
//    var sumPrice = 0
//    var sumWeight = 0
//    var newName = ""
//    val res = mutableSetOf<String>()
//    for ((name, pair) in treasures){
//        for ((name1, pair1) in treasures - name){
//            newName = "$name и $name1"
//            sumPrice = pair.second + pair1.second
//            sumWeight = pair.first + pair1.first
//            var p = sumWeight to sumPrice
//            if (sumWeight <= capacity){
//                mapNew[newName] = p
//            }
//        }
//    }
//    return res
//}
