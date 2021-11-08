package com.shenawynkov.truecallertest.data

import com.shenawynkov.truecallertest.domain.ApiService
import com.shenawynkov.truecallertest.domain.repository.HomeRepo

class HomeRepoImpl(private val apiService: ApiService) : HomeRepo {
    override suspend fun getContent() = apiService.getStringResponse()


}