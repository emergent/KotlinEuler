package org.nitamago.android.kotlineuler

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.*;

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun updateAnswer(num: Int) {
        answerArea.text = solve(num)
    }

    fun p001(view: View) { updateAnswer(1) }
    fun p002(view: View) { updateAnswer(2) }
    fun p003(view: View) { updateAnswer(3) }
    fun p004(view: View) { updateAnswer(4) }
    fun p005(view: View) { updateAnswer(5) }
    fun p006(view: View) { updateAnswer(6) }
    fun p007(view: View) { updateAnswer(7) }
    fun p008(view: View) { updateAnswer(8) }
    fun p009(view: View) { updateAnswer(9) }

    fun solve(num: Int): String {
        return when (num) {
            1 -> problem001()
            2 -> problem002()
            3 -> problem003()
            4 -> problem004()
            5 -> problem005()
            6 -> problem006()
            7 -> problem007()
            8 -> problem008()
            9 -> problem009()
            else -> "error"
        }.toString()
    }

    fun problem001(): Int {
        fun summul1(n: Int, x: Int): Int {
            return (x * (n / x) * (n / x + 1)) / 2
        }

        fun summul2(n: Int, x: Int, y: Int): Int {
            return summul1(n, x) + summul1(n, y) - summul1(n, x * y)
        }

        return summul2(999, 3, 5)
    }

    fun problem002(): Int {
        fun fibevensum2(a: Int, b: Int, sum: Int, xmax: Int): Int {
            return if (a >= xmax) {
                sum
            } else if (a % 2 == 0) {
                fibevensum2(b, a + b, sum + a, xmax)
            } else {
                fibevensum2(b, a + b, sum, xmax)
            }
        }

        fun fibevensum(xmax: Int) = fibevensum2(1, 2, 0, xmax)

        return fibevensum(4000000)
    }

    fun problem003(): Long {
        fun maxprimefactor(x: Long): Long {
            var divider: Long = 2
            var target: Long = x

            while (target != 1L) {
                if (target % divider == 0L) {
                    target /= divider
                } else {
                    divider += 1
                }
            }
            return divider
        }

        return maxprimefactor(600851475143)
    }

    fun problem004(): Int {
        fun isPalindromic(x: Int): Boolean {
            return x.toString().reversed() == x.toString()
        }
        val pmax = (100..1000).map { i ->
            (i..1000).map { j -> i * j }
        }
            .flatten()
            .distinct()
            .filter { isPalindromic(it) }
            .max() ?: 0

        return pmax
    }

    fun problem005(): Long {
        fun gcd(x: Long, y: Long): Long {
            return if (x == 0L) { y } else { gcd(y % x, x) }
        }

        fun lcm(x: Long, y: Long): Long {
            return x * y / gcd(x, y)
        }

        return (1L..20).fold(1L, { x, y -> lcm(x, y) })
    }

    fun problem006(): Int {
        val limit = 100;
        val sum = (1 + limit) * limit / 2
        val sumsq = sum * sum
        val sqsum = (1..limit).map { it * it }.sum()
        return sumsq - sqsum
    }

    fun problem007(): Int {
        fun is_prime(x: Int): Boolean {
            return when {
                x <= 1 -> false
                x == 2 -> true
                x % 2 == 0 -> false
                else -> {
                    val lim = ceil(sqrt(x.toDouble())).toInt()
                    (3..lim).step(2)
                        .all { x % it != 0 }
                }
            }
        }

        fun prime_index(n: Int): Int {
            if (n == 1) { return 2 }

            var i = 1
            var num = 3
            while (true) {
                if (is_prime(num)) { i++ }
                if (i >= n) { break }
                num += 2
            }
            return num
        }
        return prime_index(10001)
    }

    fun problem008(): Long {
        val digits = ("73167176531330624919225119674426574742355349194934\n" +
                "96983520312774506326239578318016984801869478851843\n" +
                "85861560789112949495459501737958331952853208805511\n" +
                "12540698747158523863050715693290963295227443043557\n" +
                "66896648950445244523161731856403098711121722383113\n" +
                "62229893423380308135336276614282806444486645238749\n" +
                "30358907296290491560440772390713810515859307960866\n" +
                "70172427121883998797908792274921901699720888093776\n" +
                "65727333001053367881220235421809751254540594752243\n" +
                "52584907711670556013604839586446706324415722155397\n" +
                "53697817977846174064955149290862569321978468622482\n" +
                "83972241375657056057490261407972968652414535100474\n" +
                "82166370484403199890008895243450658541227588666881\n" +
                "16427171479924442928230863465674813919123162824586\n" +
                "17866458359124566529476545682848912883142607690042\n" +
                "24219022671055626321111109370544217506941658960408\n" +
                "07198403850962455444362981230987879927244284909188\n" +
                "84580156166097919133875499200524063689912560717606\n" +
                "05886116467109405077541002256983155200055935729725\n" +
                "71636269561882670428252483600823257530420752963450")
            .trim()
            .replace("\n", "")

        var prodmax = 0L
        for (i in 0 .. (digits.length - 13)) {
            val product = digits
                .slice(i .. i + 12)
                .map { it.toLong() - 48 }
                .fold(1L, { p, x -> p * x })
            prodmax = if (prodmax < product) product else prodmax
        }
        return prodmax
    }

    fun problem009(): Int {
        fun pythagorean_triplet(c: Int): Triple<Int, Int, Int>? {
            for (b in 4..(c-1)) {
                for (a in 3..(b-1)) {
                    if (a * a + b * b == c * c) {
                        return Triple(a, b, c)
                    }
                }
            }
            return null
        }

        val abc_list = (5..999)
            .map { pythagorean_triplet(it)?.toList() }
            .filterNotNull()
            .filter { 1000 % it.sum() == 0 }
            .first()
        val quot = 1000 / abc_list.sum()
        val abc = abc_list
            .map { it * quot }
            .reduce { acc, x -> acc * x }
        return abc
    }
}
