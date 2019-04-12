package com.diegocunha.githubapp.view.repository

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.diegocunha.githubapp.databinding.ItemRepositoryBinding
import com.diegocunha.githubapp.model.data.GitRepo
import com.diegocunha.githubapp.view.databinding.ReactiveAdapter

class RepositoryAdapter : ReactiveAdapter<GitRepo, ItemRepositoryBinding>() {

    private val _onClickListener = MutableLiveData<RepositoryNavParam>()
    val onClickListener: LiveData<RepositoryNavParam> = _onClickListener


    override fun getBinding(context: Context, parent: ViewGroup, viewType: Int): ItemRepositoryBinding {
        return ItemRepositoryBinding.inflate(LayoutInflater.from(context), parent, false)
    }

    override fun bind(binding: ItemRepositoryBinding, item: GitRepo) {
        val viewModel = RepositoryItemViewModel(item)
        binding.viewModel = viewModel

        binding.root.setOnClickListener{
            val params = RepositoryNavParam(item.owner.name, item.name)
            _onClickListener.postValue(params)
        }
    }

    data class RepositoryNavParam(val ownerName: String,
                                  val repository: String)
}