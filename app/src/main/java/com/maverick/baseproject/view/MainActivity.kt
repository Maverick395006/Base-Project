package com.maverick.baseproject.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.maverick.baseproject.databinding.ActivityMainBinding
import com.maverick.baseproject.model.Country
import com.maverick.baseproject.util.DataState
import com.maverick.baseproject.util.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        subscribeObservers()
        viewModel.setStateEvent(MainStateEvent.GetCountryList)
    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(this) { dataState ->
            when (dataState) {
                is DataState.Success<List<Country>> -> {
                    displayProgressBar(false)
                    appendCountryTitles(dataState.data)
                    showToast("Successfully data fetched", true)
                }
                is DataState.Error -> {
                    displayProgressBar(false)
                    displayError(dataState.exception.message)
                    showToast("There is Some Error", true)
                }
                is DataState.Loading -> {
                    displayProgressBar(true)
                    binding.tvData.text = "Loading..."
                    showToast("Processing...", false)
                }
            }
        }
    }

    private fun displayError(message: String?) {
        binding.tvData.text = message ?: "Unknown error"
    }

    private fun displayProgressBar(isDisplayed: Boolean) {
        binding.progressBar.visibility = if (isDisplayed) View.VISIBLE else View.GONE
    }


    private fun appendCountryTitles(countries: List<Country>) {
        val sb = StringBuilder()
        for (country in countries) {
            sb.append(country.countryName + "\n")
        }
        binding.tvData.text = sb.toString()
    }

}