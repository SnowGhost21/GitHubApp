package com.diegocunha.githubapp.view.repository

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.diegocunha.githubapp.databinding.FragmentRepositoryDetailBinding
import org.koin.android.viewmodel.ext.android.viewModel

class RepositoryDetailFragment: Fragment() {

    private val viewModel: RepositoryDetailViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentRepositoryDetailBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        return binding.root
    }
}