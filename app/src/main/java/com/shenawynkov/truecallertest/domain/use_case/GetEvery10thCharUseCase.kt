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

class GetEvery10thCharUseCase @Inject constructor(private val homeRepo: HomeRepo) {
    operator fun invoke(): Flow<Resource<List<Char>>> = flow {
        try {
            //emit loading until receiving data
            emit(Resource.Loading<List<Char>>())
            val response = homeRepo.getContent()
            val doc: Document = Jsoup.parse(response)
            //emit success with every 10th char
            emit(Resource.Success(getEvery10thChar(doc.text())))
        } catch (e: HttpException) {
            emit(Resource.Error<List<Char>>(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error<List<Char>>("Couldn't reach server. Check your internet connection."))
        }
    }

    private fun getEvery10thChar(text: String): List<Char> {
        val list = ArrayList<Char>()
        var i = 9
        val length = text.length
        while (i < length) {
            list.add(text[i])
            i += 10
        }
        return list
    }

}