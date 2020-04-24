package com.example.android.guesstheword.screens.game

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    companion object {
        // These represent different important times
        // This is when the game is over
        const val DONE = 0L
        // This is the number of milliseconds in a second
        const val ONE_SECOND = 1000L
        // This is the total time of the game
        const val COUNTDOWN_TIME = 5000L
    }

    private val timer: CountDownTimer

    private val _currentTime = MutableLiveData<Long>()
    val currentTime: LiveData<Long>
        get() = _currentTime

    // The current word
    var word = MutableLiveData<String>()

    // The current score
    var score = MutableLiveData<Int>(

    )
    private val _eventGameFinish = MutableLiveData<Boolean>()
    val eventGameFinish: LiveData<Boolean>
        get() = _eventGameFinish

    // The list of words - the front of the list is the next word to guess
    private lateinit var wordList: MutableList<String>


    init {
        resetList()
        nextWord()
        score.value = 0

        Log.i("GameModelView","GameModelView init: crated")

        timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {
            override fun onTick(p0: Long) {
                Log.i("GameTimer","onTick")
                _currentTime.value = (p0 / ONE_SECOND)
            }

            override fun onFinish() {
                Log.i("GameTimer","onFinish")
                _currentTime.value = DONE
                _eventGameFinish.value = true
            }

        }
        timer.start()
    }

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
        Log.i("GameModelView","GameModelView onCLeared(): destroy")
    }

    /**
     * Resets the list of words and randomizes the order
     */
    private fun resetList() {
        wordList = mutableListOf(
                "queen",
                "hospital",
                "basketball",
                "cat",
                "change",
                "snail",
                "soup",
                "calendar",
                "sad",
                "desk",
                "guitar",
                "home",
                "railway",
                "zebra",
                "jelly",
                "car",
                "crow",
                "trade",
                "bag",
                "roll",
                "bubble"
        )
        wordList.shuffle()
    }

    /**
     * Moves to the next word in the list
     */
    private fun nextWord(): Boolean {
        //Select and remove a word from the list
        if (!wordList.isEmpty()) {
            word.value = wordList.removeAt(0)
            return true
        }
        _eventGameFinish.value = true
        return false
    }

    /** Methods for buttons presses **/

    fun onSkip(): Boolean {
        score.value = score.value?.minus(1)
        return nextWord()
    }

    fun onCorrect(): Boolean {
        score.value = score.value?.plus(1)
        return nextWord()
    }

    fun onGameFinishComplete() {
        _eventGameFinish.value = false
    }
}