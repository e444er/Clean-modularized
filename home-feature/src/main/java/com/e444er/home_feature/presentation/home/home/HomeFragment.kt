package com.e444er.home_feature.presentation.home.home

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.e444er.home_feature.R
import com.e444er.home_feature.databinding.HomeFragmentBinding
import com.e444er.home_feature.presentation.home.adapter.HomeAdapter
import com.e444er.home_feature.presentation.home.di.homeModule
import com.e444er.home_feature.presentation.home.viewBinding
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class HomeFragment : Fragment(R.layout.home_fragment) {

    private val binding: HomeFragmentBinding by viewBinding()
    private val mAdapter by lazy { HomeAdapter() }
    private val viewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadKoinModules(module = homeModule)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.movieRv.apply {
            adapter = mAdapter
            setHasFixedSize(true)
        }
        viewModel.getMovies()
        getMovie()
    }

    private fun getMovie() {
        lifecycleScope.launchWhenStarted {
            viewModel.moviesListStateFlow.collectLatest {
                if (it.isLoading) {
                    binding.progressBar.visibility = View.VISIBLE
                }
                if (it.error.isNotBlank()) {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), it.error, Toast.LENGTH_LONG).show()
                }
                it.data?.let {
                    binding.progressBar.visibility = View.GONE
                    val de = mAdapter.differ.submitList(it)
                    Log.d("Tagg", "$de")
                }
            }
        }
    }
}