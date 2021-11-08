package com.shenawynkov.truecallertest.domain.repository

interface HomeRepo {
    suspend fun getContent() :String

}