package com.tamj0rd2.skullkingcalculator.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class GameSetupViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    val players = listOf<Player>()

    val something: String = savedStateHandle["something"]
        ?: throw java.lang.IllegalArgumentException("I don't understand")

}

data class Player(val id: Int, var name: String = "")
