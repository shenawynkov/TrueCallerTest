package com.shenawynkov.truecallertest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.shenawynkov.truecallertest.R
import com.shenawynkov.truecallertest.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: HomeViewModel

    @Inject
    lateinit var factory: HomeViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView<ActivityMainBinding>(
            this,
            R.layout.activity_main
        )
        viewModel = ViewModelProvider(this, factory).get(HomeViewModel::class.java)
        binding.viewmodel=viewModel
        binding.lifecycleOwner=this
    }
}