package com.example.pokemonapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapp.models.Abilities
import com.example.pokemonapp.models.Ability
import com.example.pokemonapp.models.Pokemon
import com.example.pokemonapp.models.Sprites
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel: ViewModel()
{
    // Store the Pokemon object in the View Model
    private val _pokemon = MutableStateFlow<Pokemon?>(null) // it can only be viewed and changed from inside this View Model class
    val pokemon = _pokemon.asStateFlow() // the UI (screens) can only read/collect it


    // Constructor
    init {

        // Initialize a Pokemon
        val pokemonDitto = Pokemon(
            name = "Ditto",
            Sprites(front_default = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/132.png"),
            abilities = listOf(
                Abilities(
                    is_hidden = false,
                    slot = 1,
                    ability = Ability(name = "limber")
                ),
                Abilities(
                    is_hidden = true,
                    slot = 3,
                    ability = Ability(name = "imposter")
                )
            )
        )
        _pokemon.value = pokemonDitto




        // Start co-routine to delay 10 seconds
        viewModelScope.launch {
            delay(10000)

            // Create a new Pokemon object and update the View Model
            val pokemonPikachu = Pokemon(
                name = "Pikachu",
                Sprites(front_default = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/25.png"),
                abilities = listOf(
                    Abilities(
                        is_hidden = false,
                        slot = 1,
                        ability = Ability(name = "static")
                    ),
                    Abilities(
                        is_hidden = true,
                        slot = 3,
                        ability = Ability(name = "lightning-rod")
                    )
                )
            )
            _pokemon.value = pokemonPikachu
        }

    }
}