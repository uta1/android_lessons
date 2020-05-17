package com.example.doceroller3

import android.icu.text.IDNA
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.ImageView
import android.widget.Toast
import java.lang.Error
import java.util.*
import java.util.logging.Logger

class MainActivity : AppCompatActivity() {

    lateinit var lastRolledText: TextView
    lateinit var diceImage: ImageView

    fun getImageByNumber(number: Int): Int {
        return when (number) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
    }

    fun rollDice() {
        var newNumber = Random().nextInt(6) + 1
        lastRolledText.text = newNumber.toString()
        diceImage.setImageResource(getImageByNumber(newNumber))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var rollButton: Button = findViewById(R.id.roll_button)
        lastRolledText = findViewById(R.id.last_rolled_text)
        diceImage = findViewById(R.id.dice_image)
        rollButton.setOnClickListener {
            var toastMessage = Toast.makeText(applicationContext, "Rolled", Toast.LENGTH_SHORT)
            toastMessage.show()
            rollDice()
        }
    }
}
