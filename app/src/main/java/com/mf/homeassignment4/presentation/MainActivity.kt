package com.mf.homeassignment4.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mf.homeassignment4.data.models.UiStates
import com.mf.homeassignment4.domain.view_models.SearchScreenViewModel
import com.mf.homeassignment4.presentation.theme.HomeAssignment4Theme
import com.mf.homeassignment4.presentation.ui.Grid
import com.mf.homeassignment4.presentation.ui.Searchbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import androidx.compose.runtime.getValue
import  androidx.compose.runtime.setValue
import com.mf.homeassignment4.data.models.SearchResultModel
import com.mf.homeassignment4.presentation.ui.DetailsView

@AndroidEntryPoint()
class MainActivity : ComponentActivity() {
    private lateinit var searchScreenViewModel: SearchScreenViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HomeAssignment4Theme(darkTheme = false){
                MainScreen()
            }
        }
    }
    @Composable
    fun MainScreen(){
        searchScreenViewModel  = hiltViewModel<SearchScreenViewModel>()
        var dataItems = (searchScreenViewModel.tagsSearchData.value as? UiStates.Success)?.data?.items
//        val searchbarFlow: MutableSharedFlow<String> = MutableSharedFlow(replay = 1)
        val coroutineScope = rememberCoroutineScope()
        var searchTagsState by rememberSaveable { mutableStateOf("") }
        var isDialogOpen by rememberSaveable { mutableStateOf(false) }
        var chosenGridItem by remember { mutableStateOf<SearchResultModel?>(null) }
//        val searchbarQueryData = searchbarFlow.collectAsState("" )
        LaunchedEffect(searchTagsState) {
            try {
                searchScreenViewModel.searchTags(searchTagsState, coroutineScope)
            }
            catch(e: Exception){
                Log.e("Error", e.message.orEmpty())
            }
        }
        Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
//            Searchbar(searchbarFlow,
            Searchbar({searchTags ->
                searchTagsState = searchTags},
            PaddingValues(25.dp))}, content = { paddingValues ->
//            if(dataItems == null){
//                showError("Data is null", paddingValues)
//            }
//            else{
                Grid(dataItems ?: emptyList(), paddingValues = paddingValues, { item: SearchResultModel ->
                    isDialogOpen = !isDialogOpen
                    chosenGridItem = item
                })
                if(isDialogOpen && chosenGridItem != null){
                    DetailsView(chosenGridItem!!, {
                        isDialogOpen = !isDialogOpen}, isDialogOpen)
                }
//            }
        })
    }

    @Composable
    fun showError(message: String, paddingValues: PaddingValues){
        Text(message, modifier = Modifier.padding(paddingValues))
    }

}
