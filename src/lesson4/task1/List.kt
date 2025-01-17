@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import java.util.*
import kotlin.math.sqrt

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
        when {
            y < 0 -> listOf()
            y == 0.0 -> listOf(0.0)
            else -> {
                val root = sqrt(y)
                // Результат!
                listOf(-root, root)
            }
        }


/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double {
    val mod = v.map{ it * it }      // новый список с квадратами, вычисленными с использованием MAP
    val mod1 = mod.fold(0.0){ res, i -> res + i} //с аккумулированный при момощи FOLD новый список со 1 значение суммой всех элементов
    return Math.sqrt(mod1)       // при возврате извлекаем из квадратного корня
}
/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    return if (list == listOf<Double>()) 0.0        // возвращаем условие если список пустой то 0
    else list.sum() / list.size                 // в противном случае среднеарефмитическую элементов списка
}

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    val s = list.average()            //среднеарефмитическая елементов списка
    for (i in 0 until list.size) {              //цикл для изменения елементов
        list[i] -= s                        // присвоение нового значения каждому элементу по очереди
    }
    return list
}

/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.0.
 */
fun times(a: List<Double>, b: List<Double>): Double {
    var C = 0.0                             // Переменная скалярного выражения
    for (i in 0 until a.size) {        // цикл для вычисления всех значений списков а и б
        C += a[i] * b[i]                // расчет по формуле C = a1b1 + a2b2 + ... + aNbN
    }
    return C
}

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0.0 при любом x.
 */
fun polynom(p: List<Double>, x: Double): Double {
    var a = 1.0                 // счетчик для степени
    var px: Double              // значение многочлена p(x)
    if (p.isNotEmpty()) {       //УСЛОВИЕ для определение наличичя значений в списке
        px = p[0]               // если есть жлементы то первый символ приравниваем p(x)
    } else return 0.0               // если пустой возвращаем 0
    for (i in 1 until p.size) {     // цикл для расчеты по формуле, для определения многочлена
        px += p[i] * Math.pow(x, a++)   // расчет формулы
    }
    return px
}

/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Double>): MutableList<Double> {
    var sum = 0.0                       // переменная сумматор
    for (i in 0 until list.size) {  // цикл для изменения списка
        sum += list[i]                  // сумируем елементы списка по индексу
        list[i] = sum                   // присваиваем значение суммы
    }
    return list
}

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    var k = n
    var a = 2
    var st = listOf<Int>()
    while (k != 1)
        if (k % a == 0) {
            st += a
            k /= a
        } else a++
    return st
}


/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String {
    var k = n                    //переменная к равная числу для уменьшения ее значения
    var a = 2                       // делитель числа, который в случае деления без остатка будет записан в строку
    var st = listOf<Int>()  // список, который будет переведен в строку для вывода ответа
    while (k != 1)                      //цикл для выявления множителей, пока результат джеления не равен 1
        if (k % a == 0) {        // условие если делитель дели без остатка
            st += a             // прибавляем делитель в строку
            k /= a              // и делим число на делитель
        } else a++              // если нет, то увеличиваем делитель на 1
    return st.joinToString(separator = "*")
}


/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    var list = listOf<Int>() // список для вывода списка результатов
    var n1 = n                      // перменная для уменьшения изначального числа в цикле
    if (n == 0) return list + 0
    while (n1 > 0) {                // цикл для перевода числа в другую систему исчисления
        var a = n1 % base       // переменная хранящая остаток от деления
        list += a                   // запись остатка от деления в результат
        n1 /= base                  // деления (и уменьшения числа)
    }
    return list.reversed()          // разворачиваем для вывода от старшей к младшей
}
/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 */
fun convertToString(n: Int, base: Int): String {
    var st: String = ""                                  // список для вывода списка результатов
    val list = listOf<String>("a", "b", "c", "d", "e",  // Список букв
            "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
            "q", "r", "s", "t", "u", "v", "w", "x", "y", "z")
    val dig = listOf<Int>(10, 11, 12, 13, 14, 15, 16, 17,   // Список цифр
            18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31,
            32, 33, 34, 35)
    var n1 = n                    // перменная для уменьшения изначального числа в цикле
    if (n == 0) return st + 0
    while (n1 > 0) {                // цикл для перевода числа в другую систему исчисления
        var a = n1 % base       // переменная хранящая остаток от деления
        if (a > 9) {                    // условие для цирф если оно больше 9 заменяем на букву
            for (i in dig.indices) {    // цикл для пробежки по списку с цифрами для определения индекса циры
                if (a == dig[i]) {          // условие если цифра равна цифре в массиве определяем индексу
                    st += list[i]       // присвоение строке буквы если соответствует условию
                    break           //останавливаем цикл если ок
                }
            }
        } else {                // если меньше 9 до остаток отделения (а) записываем в список
            st += a         // запись в список
        }
        n1 /= base                  // деления (и уменьшения числа)
    }
    return st.reversed()          // разворачиваем для вывода от старшей к младшей
}

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var y = digits.size - 1
    var i = 0
    var res = 0
    while (y >= 0) {
        res += digits[i] * Math.pow(base.toDouble(), y.toDouble()).toInt()
        y--
        i++
    }
    return res
}


/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 */
fun decimalFromString(str: String, base: Int): Int {
    var num = 0                                         //переменная куда сумируется итоговая цифра
    val list = listOf<Char>('a', 'b', 'c', 'd', 'e',  // Список букв
            'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
            'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z')
    val dig = listOf<Int>(10, 11, 12, 13, 14, 15, 16, 17,   // Список цифр
            18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31,
            32, 33, 34, 35)
    var y = str.length - 1                                 //переменная для степени
    var d:Int                                               //переменная для индекса символа и цифры
    for (j in str) {                // цикл по строке для выведения цифра
        if (list.contains(j)) {         // условия при котором если символ есть в списке, то true
            d = list.indexOf(j)         // тогда записываем индекс символа в списке
            num += dig[d] * Math.pow(base.toDouble(), y.toDouble()).toInt() // и вычисляем десятичное число этого числа
        } else num += (j.toInt() - '0'.toInt()) * Math.pow(base.toDouble(), y.toDouble()).toInt() // если нет то вычисляем десятичное число и з цифры в строке (преобразовывая цифру из символа в цифру)
        y--                                                                 // уменьшаем значение счетчика
    }
    return num
}

/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String = TODO()
//{
//    val rome = listOf<String>("I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M")
//    val arab = listOf<Int>(1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000)
//    var res = ""
//    var n1 = n
//    var b = 1
//    var i = 0
//    while (n1 != 0) {
//        var a = n1 % 10
//        if (n1 >= 1000) {
//        }
//
//    }
//    return arab.joinToString().reversed()
//}


/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String = TODO()

fun main(args: Array<String>) {

    print(russian(483648))
}