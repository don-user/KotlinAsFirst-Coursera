@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import kotlin.math.*

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result *= i
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
        when {
            n == m -> 1
            n < 10 -> 0
            else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
        }

/**
 * Тривиальная
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int {
    var count = 0
    var num = n
    do {
        num /= 10
        count++
    } while (num != 0)
    return count
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    if (n <= 2) return 1
    var a = 1
    var b = 1
    var res = 0
    for (i in 3..n) {
        res = a + b
        a = b
        b = res
    }
    return res
}


/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    var k: Int
    if (m == n) return m
    var min = Math.min(m, n)
    var max = Math.max(m, n)
    while (max != 0) {
        k = min % max
        min = max
        max = k
    }
    return m * n / min

}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    var div = 2
    while (n % div != 0) {
        div++
    }
    return div
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    var a = n - 1
    while (n % a != 0) {
        a--
    }
    return a
}

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {

    var k: Int
    var m1 = Math.min(m, n)
    var m2 = Math.max(m, n)
    while (m2 != 0) {
        k = m1 % m2
        m1 = m2
        m2 = k
    }
    return m1 == 1
}

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    var k = 0
    val n1 = sqrt(n.toDouble()).toInt()
    if (m == n) return true
    do {
        k++
        if ((k * k) >= m && (k * k) <= n) return true
    } while (k < n1)
    return false
}

/**
 * Средняя
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int {
    var cnt = 0
    var a = x
    while (a != 1) {
        if (a % 2 == 0) a /= 2
        else a = 3 * a + 1
        cnt++
    }
    return cnt
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun sin(x: Double, eps: Double): Double = TODO()

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun cos(x: Double, eps: Double): Double {
    var cosRes = 1.0
    var a = 1.0
    var i = 2.0
   do {
        a *= x * x / (2 * i * (2 * i -1))
        cosRes += Math.abs(a)
        i++
    } while (Math.abs(a) > eps)
    return cosRes               // ОШБИКИ в решении надо будет подумать как исправить
}


/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int {
    var a = 0
    var m = n
    while (m != 0) {
        a = a * 10 + m % 10
        m /= 10
    }
    return a
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean {
    var a = n
    var dig = 0
    while (a > 0) {
        dig = dig * 10 + a % 10
        a /= 10
    }
    return dig == n

}


/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean {
    var c = n % 10
    var m = n
    while (m != 0) {
        if (c != m % 10) return true
        c = m % 10
        m /= 10
    }
    return false
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun squareSequenceDigit(n: Int): Int {
    var b = 1.0
    var sum = 0
    var cnt = 0
    while (cnt < n) {
        sum = Math.pow(b, 2.0).toInt()
        when {
            sum < 10 -> cnt++
            sum < 100 -> cnt += 2
            sum < 1000 -> cnt += 3
            sum < 10000 -> cnt += 4
            sum < 100000 -> cnt += 5
            sum < 1000000 -> cnt += 6
            sum < 10000000 -> cnt += 7
            sum < 100000000 -> cnt += 8
            sum < 1000000000 -> cnt += 9
            sum < 10000000000 -> cnt += 10
        }
        b++
    }
    sum = when {
        cnt == n -> sum % 10
        cnt - 1 == n -> sum / 10 % 10
        cnt - 2 == n -> sum / 100 % 10
        cnt - 3 == n -> sum / 1000 % 10
        cnt - 4 == n -> sum / 10000 % 10
        cnt - 5 == n -> sum / 100000 % 10
        cnt - 6 == n -> sum / 1000000 % 10
        cnt - 7 == n -> sum / 10000000 % 10
        cnt - 8 == n -> sum / 100000000 % 10
        cnt - 9 == n -> sum / 1000000000 % 10
        else -> (sum / 10000000000 % 10).toInt()
    }
    return sum
}



/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int {
    if (n == 1) return 1
    var a = 0
    var b = 1
    var cnt = 1
    var sum = 0
    while (cnt < n) {
        sum = a + b
        a = b
        b = sum
        when {
            sum < 10 -> cnt++
            sum < 100 -> cnt += 2
            sum < 1000 -> cnt += 3
            sum < 10000 -> cnt += 4
            sum < 100000 -> cnt += 5
            sum < 1000000 -> cnt += 6
            sum < 10000000 -> cnt += 7
            sum < 100000000 -> cnt += 8
            sum < 1000000000 -> cnt += 9
            sum < 10000000000 -> cnt += 10
        }
        sum = when {
            cnt == n -> sum % 10
            cnt - 1 == n -> sum / 10 % 10
            cnt - 2 == n -> sum / 100 % 10
            cnt - 3 == n -> sum / 1000 % 10
            cnt - 4 == n -> sum / 10000 % 10
            cnt - 5 == n -> sum / 100000 % 10
            cnt - 6 == n -> sum / 1000000 % 10
            cnt - 7 == n -> sum / 10000000 % 10
            cnt - 8 == n -> sum / 100000000 % 10
            cnt - 9 == n -> sum / 1000000000 % 10
            else -> (sum / 10000000000 % 10).toInt()
        }
    }
    return sum
}

