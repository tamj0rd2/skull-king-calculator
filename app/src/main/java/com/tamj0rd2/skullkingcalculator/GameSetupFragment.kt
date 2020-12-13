package com.tamj0rd2.skullkingcalculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.Barrier
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.tamj0rd2.skullkingcalculator.components.PlayerInput
import com.tamj0rd2.skullkingcalculator.viewmodels.GameSetupViewModel

class GameSetupFragment : Fragment(R.layout.game_setup) {
    private val viewModel: GameSetupViewModel by viewModels()

    private lateinit var playerCountTextView: TextView
    private lateinit var topLevelLayout: ConstraintLayout
    private lateinit var removePlayerButton: Button
    private lateinit var addPlayerButton: Button
    private lateinit var playerConfigBarrier: Barrier

    private val playerInputs = mutableListOf<PlayerInput>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.topLevelLayout = view.findViewById(R.id.topLevelLayout)
        this.playerConfigBarrier = view.findViewById(R.id.playerConfig)
        this.playerCountTextView = view.findViewById(R.id.playerCount)

        this.removePlayerButton = view.findViewById(R.id.removePlayer)
        this.removePlayerButton.setOnClickListener { this.removePlayerInput() }

        this.addPlayerButton = view.findViewById(R.id.addPlayer)
        this.addPlayerButton.setOnClickListener { this.addPlayerInput() }

        this.addPlayerInput()
        this.addPlayerInput()
    }

    private fun addPlayerInput() {
        val layoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_CONSTRAINT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        layoutParams.startToStart = this.topLevelLayout.id
        layoutParams.endToEnd = this.topLevelLayout.id
        layoutParams.topToBottom = this.playerInputs.lastOrNull()?.id ?: this.playerConfigBarrier.id

        val playerInput = PlayerInput(this.context, null, this.playerInputs.size + 1)
        playerInput.id = View.generateViewId()
        playerInput.layoutParams = layoutParams

        playerInputs.add(playerInput)
        this.topLevelLayout.addView(playerInput)
        this.updateCountAndButtonStatuses()
    }

    private fun removePlayerInput() {
        val lastPlayerInput = this.playerInputs.removeAt(this.playerInputs.size - 1)
        this.topLevelLayout.removeView(lastPlayerInput)
        this.updateCountAndButtonStatuses()
    }

    private fun updateCountAndButtonStatuses() {
        this.removePlayerButton.isEnabled = this.playerInputs.size > 2
        this.addPlayerButton.isEnabled = this.playerInputs.size < 6
        this.playerCountTextView.text = this.playerInputs.size.toString()
    }
}
