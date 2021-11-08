package com.shenawynkov.truecallertest.ui

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shenawynkov.truecallertest.common.Resource
import com.shenawynkov.truecallertest.domain.use_case.Get10thCharUseCase
import com.shenawynkov.truecallertest.domain.use_case.GetEvery10thCharUseCase
import com.shenawynkov.truecallertest.domain.use_case.GetWordsCountedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val get10thCharUseCase: Get10thCharUseCase,
    private val getEvery10thCharUseCase: GetEvery10thCharUseCase,
    private val getWordsCountedUseCase: GetWordsCountedUseCase
) :
    ViewModel() {

    val char10 = MutableLiveData<String>()
    val everyChar = MutableLiveData<String>()
    val countedWords = MutableLiveData<String>()

    fun getContent()
    {
        get10thChar()
        getEvery10thChar()
        getWordsCounted()
    }

    private fun get10thChar() {
        get10thCharUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    char10.value = result.data.toString()
                }
                is Resource.Error -> {
                    char10.value = result.message
                }
                is Resource.Loading -> {
                    char10.value = "loading"
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getEvery10thChar() {
        getEvery10thCharUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    everyChar.value = result.data?.joinToString(separator = " , ")
                }
                is Resource.Error -> {
                    everyChar.value = result.message
                }
                is Resource.Loading -> {
                    everyChar.value = "loading"
                }
            }
        }.launchIn(viewModelScope)
    }
    private fun getWordsCounted() {
        getWordsCountedUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    var words=""
                    result.data?.forEach { (key, value) ->
                       words+= "$key   $value\n"
                    }
                    countedWords.value=words
                }
                is Resource.Error -> {
                    countedWords.value = result.message
                }
                is Resource.Loading -> {
                    countedWords.value = "loading"
                }
            }
        }.launchIn(viewModelScope)
    }

}