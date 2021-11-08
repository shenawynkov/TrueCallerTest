package com.shenawynkov.truecallertest.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shenawynkov.truecallertest.domain.use_case.Get10thCharUseCase
import com.shenawynkov.truecallertest.domain.use_case.GetEvery10thCharUseCase
import com.shenawynkov.truecallertest.domain.use_case.GetWordsCountedUseCase

import javax.inject.Inject

class HomeViewModelFactory @Inject constructor(
    private val get10thCharUseCase: Get10thCharUseCase,
    private val getEvery10thCharUseCase: GetEvery10thCharUseCase,
    private val getWordsCountedUseCase: GetWordsCountedUseCase
) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(
            get10thCharUseCase,
            getEvery10thCharUseCase,
            getWordsCountedUseCase
        ) as T
    }


}