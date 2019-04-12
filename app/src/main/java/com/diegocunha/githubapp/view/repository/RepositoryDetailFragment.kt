package com.diegocunha.githubapp.view.repository

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.diegocunha.githubapp.databinding.FragmentRepositoryDetailBinding
import com.diegocunha.githubapp.view.MainActivity
import org.koin.android.viewmodel.ext.android.viewModel

class RepositoryDetailFragment : Fragment() {

    private val viewModel: RepositoryDetailViewModel by viewModel()
    private val args: RepositoryDetailFragmentArgs by navArgs()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentRepositoryDetailBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val projectName = args.projectName
        val ownerName = args.projectOwner

        (activity as MainActivity).supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = projectName
        }

        viewModel.getRepositoryDetail(projectName, ownerName)

        return binding.root
    }
}