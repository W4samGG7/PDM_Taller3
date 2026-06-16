package com.pdmcourse2026.basictemplate.screens.resultados

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pdmcourse2026.basictemplate.components.OptionCard
import com.pdmcourse2026.basictemplate.components.ResultCard
import com.pdmcourse2026.basictemplate.screens.home.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultadosScreen(
    viewModel: ResultadosListViewModel = viewModel(),
    navigateToResultados: () -> Unit
) {

    val options by viewModel.options.collectAsState()

    

    val loading by viewModel.loading.collectAsState()
    val saving by viewModel.saving.collectAsState()
    val error by viewModel.error.collectAsState()
    val saveMessage by viewModel.saveMessage.collectAsState()
    val refresh by viewModel.refreshing.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadOptions()
    }

    if(loading){
        Scaffold(
            topBar = {
                TopAppBar(
                    colors = topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = { Text("Resultados") },
                )
            }
        ) { paddingValues ->
            CircularProgressIndicator(modifier = Modifier.padding(paddingValues))
        }
        return
    }

    if (error != null){
        Scaffold(
            topBar = {
                TopAppBar(
                    colors = topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = { Text("Resultados") },
                )
            }
        ) { paddingValues ->
            Column(modifier =
                Modifier.padding(paddingValues).padding(16.dp).background(Color.White),
                verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "${error}")
                Button(onClick = {
                    viewModel.loadOptions()
                }) {
                    Text(
                        text = "Reintentar",
                        color = Color.Cyan
                    )
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = { Text("Resultados") },
            )
        }
    ) { innerPadding ->
        PullToRefreshBox(
            isRefreshing = refresh,
            onRefresh = {viewModel.refreshVotes()},
            modifier = Modifier
        ) {
            Column(
                modifier = Modifier.padding(innerPadding).fillMaxSize().height(300.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LazyColumn(
                    modifier = Modifier.height(600.dp),
                    contentPadding = PaddingValues(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(options) { option ->
                        ResultCard(option = option, onClick = {})
                    }
                }
                Button(
                    onClick = { navigateToResultados() }
                ) {
                    Text(text = "Nuevo (Volver a votar)")
                }
            }
        }
    }
}