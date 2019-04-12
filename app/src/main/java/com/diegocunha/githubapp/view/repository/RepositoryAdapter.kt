package com.diegocunha.githubapp.view.repository

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.diegocunha.githubapp.databinding.ItemRepositoryBinding
import com.diegocunha.githubapp.model.data.RetrofitRepository
import com.diegocunha.githubapp.view.databinding.ReactiveAdapter

class RepositoryAdapter : ReactiveAdapter<RetrofitRepository, ItemRepositoryBinding>() {

    override fun getBinding(context: Context, parent: ViewGroup, viewType: Int): ItemRepositoryBinding {
        return ItemRepositoryBinding.inflate(LayoutInflater.from(context), parent, false)
    }

    override fun bind(binding: ItemRepositoryBinding, item: RetrofitRepository) {
        val viewModel = RepositoryItemViewModel(item)
        binding.viewModel = viewModel
    }
}