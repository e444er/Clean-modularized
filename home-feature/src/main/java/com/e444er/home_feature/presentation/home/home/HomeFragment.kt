package com.e444er.home_feature.presentation.home.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.coroutineScope
import com.e444er.home_feature.R
import com.e444er.home_feature.databinding.HomeFragmentBinding
import com.e444er.home_feature.presentation.home.adapter.HomeAdapter
import com.e444er.home_feature.presentation.home.di.homeModule
import com.e444er.home_feature.presentation.home.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class HomeFragment : Fragment(R.layout.home_fragment) {

    private val binding: HomeFragmentBinding by viewBinding()
    private val _adapter by lazy { HomeAdapter() }
    private val viewModel: HomeViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadKoinModules(module = homeModule)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.movieRv.adapter = _adapter
        getMovies()
        viewModel.getMovies("popular")
    }

    private fun getMovies() {

        lifecycle.coroutineScope.launchWhenCreated {
            viewModel.moviesListStateFlow.collect { it ->
                if (it.isLoading) {
                    binding.progressBar.isVisible = true
                    binding.movieRv.isVisible = false
                }
                if (it.error.isNotBlank()) {
                    binding.progressBar.isVisible = false
                    binding.movieRv.isVisible = false
                    Toast.makeText(requireContext(), "$it", Toast.LENGTH_SHORT).show()

                }
                it.data?.let {
                    binding.progressBar.isVisible = false
                    binding.movieRv.isVisible = true
                    _adapter.differ.submitList(it)
                }
            }
        }
    }

}