package com.mf.homeassignment4.data.repos.network_repos

import com.mf.homeassignment4.data.models.SearchResultsModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("services/feeds/photos_public.gne?format=json&nojsoncallback=1")
    suspend fun searchByTags(@Query("tags") tags: String) : Response<SearchResultsModel>

}