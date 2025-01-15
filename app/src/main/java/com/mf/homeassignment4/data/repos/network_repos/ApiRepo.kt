package com.mf.homeassignment4.data.repos.network_repos

import android.accounts.NetworkErrorException
import com.mf.homeassignment4.data.models.SearchResultsModel
import com.mf.homeassignment4.data.models.UiStates
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

class ApiRepo @Inject constructor(val apiService: ApiService) {

    suspend fun searchByTags(tags: String): SearchResultsModel?{
        val searchByTagsResponse = apiService.searchByTags(tags)
        if(searchByTagsResponse.isSuccessful){
            val jsonResponse = searchByTagsResponse.body()
            if(jsonResponse != null)
                return jsonResponse
        }
        else if(searchByTagsResponse.errorBody() != null)
            throw HttpException(searchByTagsResponse)
        else
            throw NetworkErrorException("An Unknown network error has occurred")
        return null
    }
}