package com.example.pokemonapp.services

import com.example.pokemonapp.models.Pokemon
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonService {

    // ..pokemon/ditto
    @GET("pokemon/{name}")
    suspend fun getPokemon(
        @Path("name") name: String
    ): Pokemon

    // ..pokemon?limit=##&offset=##
    @GET("pokemon")
    suspend fun getAllPokemon(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): List<Pokemon>
}