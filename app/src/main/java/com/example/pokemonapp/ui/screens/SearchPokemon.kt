package com.example.pokemonapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

    // Search text
    var searchText by remember { mutableStateOf("") }

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

        // Pokemon search form
        Row(
            modifier = Modifier
                .padding(innerPadding)
        )
        {
            TextField(
                value = searchText,
                onValueChange = { newText ->
                    searchText = newText
                },
                label = { Text("Search a Pokemon character") }
            )

            Button(
                onClick = {
                    // Search Pokemon in API by name
                    mainViewModel.searchPokemon( name = searchText)
                }
            )
            {
                Text("Search")
            }
        }

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            if(pokemon != null) {
                Text(
                    text = pokemon?.name.toString(),
                    style = MaterialTheme.typography.headlineLarge
                )

                Image(
                    painter = rememberAsyncImagePainter(pokemon?.sprites?.frontDefault),
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

}