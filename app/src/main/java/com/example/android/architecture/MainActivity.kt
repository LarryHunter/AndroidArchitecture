package com.example.android.architecture

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.android.architecture.util.DiceHelper
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private val imageViews by lazy {
        arrayOf<ImageView>(die1, die2, die3, die4, die5)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        this.title = getString(R.string.app_name)
        headline.text = getString(R.string.welcome)

        fab.setOnClickListener { view ->
            val dice = DiceHelper.rollDice()
            updateDisplay(dice)
        }

        lifecycle.addObserver(MyLifeCycleObserver())

        for (imageView in imageViews) {
            imageView.setImageResource(R.drawable.die_6)
        }
    }

    private fun updateDisplay(dice: IntArray) {
        for (i in imageViews.indices) {
            val drawableId = when (dice[i]) {
                1 -> R.drawable.die_1
                2 -> R.drawable.die_2
                3 -> R.drawable.die_3
                4 -> R.drawable.die_4
                5 -> R.drawable.die_5
                else -> R.drawable.die_6
            }
            imageViews[i].setImageResource(drawableId)
        }
        headline.text = DiceHelper.evaluateDice(this, dice)
    }
}
