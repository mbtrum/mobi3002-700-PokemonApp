package com.example.pokemonapp.models

import com.google.gson.annotations.SerializedName

data class Pokemon(
    val name: String,
    val height: Int,
    val weight: Int,

    val sprites: Sprites,
    val abilities: List<Abilities>
)

data class Sprites(
    @SerializedName("front_default") val frontDefault: String
)

data class Abilities(
    @SerializedName("is_hidden") val isHidden: Boolean,
    val slot: Int,

    val ability: Ability
)

data class Ability(
    val name:String
)