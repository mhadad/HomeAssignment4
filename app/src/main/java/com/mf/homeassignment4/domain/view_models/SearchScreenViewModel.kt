package com.mf.homeassignment4.domain.view_models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mf.homeassignment4.data.models.SearchResultsModel
import com.mf.homeassignment4.data.models.UiStates
import com.mf.homeassignment4.data.repos.network_repos.ApiRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel()
class SearchScreenViewModel @Inject constructor(val apiRepo: ApiRepo): ViewModel() {
    private val _tagsSearchData : MutableLiveData<UiStates<SearchResultsModel>> = MutableLiveData()
    val tagsSearchData : LiveData<UiStates<SearchResultsModel>> = _tagsSearchData

    fun searchTags(tags: String, coroutinesScope: CoroutineScope){
        coroutinesScope.launch(Dispatchers.IO){
            try {
                val searchByTagsResponse = apiRepo.searchByTags(tags)
                if(searchByTagsResponse != null){
                    _tagsSearchData.postValue(UiStates.Success(searchByTagsResponse))
                }
                else {
                    _tagsSearchData.postValue((UiStates.Error("Data was empty")))
                    Log.e("Error", "Data was empty")
                }
            }
            catch(e: Exception){
                Log.e("Error", e.message.orEmpty())
            }

        }
    }
}