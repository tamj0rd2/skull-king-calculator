package com.tamj0rd2.skullkingcalculator.viewmodels

import android.os.Parcel
import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class GameSetupViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    val players = listOf<Player>()

    val something: String = savedStateHandle["something"]
        ?: throw java.lang.IllegalArgumentException("I don't understand")

}

data class Player(val id: Int, var name: String = "") : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Player> {
        override fun createFromParcel(parcel: Parcel): Player {
            return Player(parcel)
        }

        override fun newArray(size: Int): Array<Player?> {
            return arrayOfNulls(size)
        }
    }

}
