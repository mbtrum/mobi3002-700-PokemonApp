package com.example.pokemonapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapp.models.Abilities
import com.example.pokemonapp.models.Ability
import com.example.pokemonapp.models.Pokemon
import com.example.pokemonapp.models.Sprites
import com.example.pokemonapp.services.PokemonService
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel: ViewModel()
{
    // Store the Pokemon object in the View Model
    private val _pokemon = MutableStateFlow<Pokemon?>(null) // it can only be viewed and changed from inside this View Model class
    val pokemon = _pokemon.asStateFlow() // the UI (screens) can only read/collect it

    // Retrofit instance
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // Pokemon service (interface)
    val pokemonService: PokemonService = retrofit.create(PokemonService::class.java)

    // Constructor
//    init {
//        viewModelScope.launch {
//            // Call the Pokemon API to get Ditto
//            val pokemon = pokemonService.getPokemon()
//
//            // Update the Pokemon object in the View Model
//            _pokemon.value = pokemon
//        }
//    }

    fun searchPokemon(name: String) {
        viewModelScope.launch {
            // Call the Pokemon API to get by name
            val pokemon = pokemonService.getPokemon(name)

            // Update the Pokemon object in the View Model
            _pokemon.value = pokemon
        }
    }

}