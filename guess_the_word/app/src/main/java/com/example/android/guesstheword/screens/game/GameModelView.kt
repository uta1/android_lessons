package com.example.android.guesstheword.screens.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
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
    }

    override fun onCleared() {
        super.onCleared()
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