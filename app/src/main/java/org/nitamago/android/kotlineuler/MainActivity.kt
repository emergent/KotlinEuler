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

    fun solve(num: Int): String {
        return when (num) {
            1 -> problem001()
            2 -> problem002()
            3 -> problem003()
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
}
