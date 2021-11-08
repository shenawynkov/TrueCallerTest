package com.shenawynkov.truecallertest.domain.use_case

import com.shenawynkov.truecallertest.common.Resource
import com.shenawynkov.truecallertest.domain.repository.HomeRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class Get10thCharUseCase @Inject constructor(private val homeRepo: HomeRepo) {
    operator fun invoke(): Flow<Resource<Char>> = flow {
        try {
            emit(Resource.Loading<Char>())
            val response = homeRepo.getContent()
            val doc: Document = Jsoup.parse(response)
           val char=  doc.text()[9]
            emit(Resource.Success(char))
        } catch (e: HttpException) {
            emit(Resource.Error<Char>(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error<Char>("Couldn't reach server. Check your internet connection."))
        }
    }


}