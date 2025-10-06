package com.example.pokemonapp.models

data class Pokemon(
    val name: String,

    val sprites: Sprites,
    val abilities: List<Abilities>
)

data class Sprites(
    val front_default: String
)

data class Abilities(
    val is_hidden: Boolean,
    val slot: Int,

    val ability: Ability
)

data class Ability(
    val name:String
)