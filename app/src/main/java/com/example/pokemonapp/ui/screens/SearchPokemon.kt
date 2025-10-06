package com.example.pokemonapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.pokemonapp.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchPokemon(mainViewModel: MainViewModel) {

    // Get the pokemon object from state flow
    val pokemon by mainViewModel.pokemon.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Pokemon App") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                )
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(text = pokemon?.name.toString(), style = MaterialTheme.typography.headlineLarge)

            Image(
                painter = rememberAsyncImagePainter(pokemon?.sprites?.front_default),
                contentDescription = pokemon?.name.toString(),
                Modifier.size(128.dp)
            )

            Text("Abilities:", style = MaterialTheme.typography.headlineSmall)

            // display list of abilities
            for (item in pokemon?.abilities!!) {
                Text(text = item.ability.name)
            }

        }

    }

}