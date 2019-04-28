package org.nitamago.android.kotlineuler

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

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

    fun solve(num: Int): String {
        return when (num) {
            1 -> problem001()
            2 -> problem002()
            3 -> problem003()
            4 -> problem004()
            5 -> problem005()
            6 -> problem006()
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
}
