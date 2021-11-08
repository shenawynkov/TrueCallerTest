package com.shenawynkov.truecallertest.domain

import com.shenawynkov.truecallertest.common.Constants.blog
import retrofit2.http.GET




interface ApiService {
    @GET(blog)
    suspend fun getStringResponse(): String
}
