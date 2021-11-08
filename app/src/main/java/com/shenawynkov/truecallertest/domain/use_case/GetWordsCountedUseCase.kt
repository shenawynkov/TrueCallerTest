package com.shenawynkov.truecallertest.domain.use_case

import com.shenawynkov.truecallertest.common.Resource
import com.shenawynkov.truecallertest.domain.repository.HomeRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.jsoup.Jsoup
import org.jsoup.internal.StringUtil
import org.jsoup.nodes.Document
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetWordsCountedUseCase @Inject constructor(private val homeRepo: HomeRepo) {
    operator fun invoke(): Flow<Resource<HashMap<String, Int>>> = flow {
        try {
            //emit loading until receiving data
            emit(Resource.Loading<HashMap<String, Int>>())
            val response = homeRepo.getContent()
            val doc: Document = Jsoup.parse(response)
            //emit success with hash map of words and it is count
            emit(Resource.Success(createHashMap(doc.text())))
        } catch (e: HttpException) {
            emit(
                Resource.Error<HashMap<String, Int>>(
                    e.localizedMessage ?: "An unexpected error occured"
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error<HashMap<String, Int>>("Couldn't reach server. Check your internet connection."))
        }
    }


    private fun createHashMap(text: String): HashMap<String, Int> {

        val occurrences: HashMap<String, Int> = HashMap()
        val splitWords = text.split(" ").toTypedArray()
        for (word in splitWords) {
            //check if the word already exist on map if so modify it is count if not init count = 0
            var oldCount: Int? = occurrences.get(word)
            if (oldCount == null) {
                oldCount = 0
            }
            occurrences.put(word, oldCount + 1)
        }
        return occurrences
    }
}