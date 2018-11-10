package com.example.madina.connect2kotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    // 0: yellow, 1: red

    var activePlayer: Int = 0;
    val gameState: Array<Int> = arrayOf(2, 2, 2, 2, 2, 2, 2, 2, 2)
    var gameActive: Boolean = true
    var winningPositions = arrayOf(intArrayOf(0, 1, 2), intArrayOf(3, 4, 5), intArrayOf(6, 7, 8), intArrayOf(0, 3, 6), intArrayOf(1, 4, 7), intArrayOf(2, 5, 8), intArrayOf(0, 4, 8), intArrayOf(2, 4, 6))

    fun dropIn(view: View) {

        val counter = view as ImageView
        val tappedCounter: Int = counter.tag.toString().toInt()

        if(gameActive){
            counter.translationY = -1500f
            counter.setImageResource(R.drawable.yellow)
            counter.animate().translationYBy(1500f).duration=300

            if(activePlayer == 0){
                counter.setImageResource(R.drawable.yellow)
                activePlayer = 1
            }else{
                counter.setImageResource(R.drawable.red)
                activePlayer = 0
            }
            gameState[tappedCounter] = activePlayer

            for(position in winningPositions){
                if(gameState[position[0]] != 2 && gameState[position[0]] == gameState[position[1]] && gameState[position[1]] == gameState[position[2]]){
                    gameActive = false
                    var winner: String = ""

                    if(activePlayer == 0)
                        winner = "Red"
                    else
                        winner = "Yellow"

                    winnerTextView.text = winner + " has won!"
                    winnerTextView.visibility = View.VISIBLE
                    playAgainButton.visibility = View.VISIBLE
                }
            }
        }


    }

    fun playAgain(view: View) {
        winnerTextView.visibility = View.INVISIBLE
        playAgainButton.visibility = View.INVISIBLE
        activePlayer = 0;
        gameActive = true

        for (i in 0 until gridLayout.getChildCount()) {
            val counter = gridLayout.getChildAt(i) as ImageView
            counter.setImageDrawable(null)

        }

        for(i in 0 until gameState.size)
            gameState[i] = 2

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
