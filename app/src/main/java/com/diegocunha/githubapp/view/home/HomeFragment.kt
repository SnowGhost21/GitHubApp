package com.diegocunha.githubapp.view.home

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuItemCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.diegocunha.githubapp.R
import com.diegocunha.githubapp.databinding.FragmentHomeBinding
import com.diegocunha.githubapp.view.MainActivity
import com.diegocunha.githubapp.view.repository.RepositoryAdapter
import org.koin.android.viewmodel.ext.android.viewModel


class HomeFragment : Fragment(), SearchView.OnQueryTextListener, SearchView.OnCloseListener, View.OnClickListener {

    private val viewModel: HomeViewModel by viewModel()
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val adapter = RepositoryAdapter()
        binding.repositoriesRecyclerView.adapter = adapter

        viewModel.repositories.observe(this, Observer {
            if (it == null) {
                adapter.clearItems()
            } else {
                adapter.setItems(ArrayList(it))
            }
        })

        binding.repositoriesRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val manager = recyclerView.layoutManager as? LinearLayoutManager
                manager?.let {
                    val visibleItemCount = it.childCount
                    val totalItemCount = it.itemCount

                    val firstVisibleItemPosition = it.findFirstVisibleItemPosition()

                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount) {
                        viewModel.getRepositories(null)
                    }
                }
            }

        })

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.menu_home, menu)
        val item = menu.findItem(R.id.action_search)
        searchView = SearchView((activity as MainActivity).supportActionBar!!.themedContext)
        MenuItemCompat.setShowAsAction(item, MenuItemCompat.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW or MenuItemCompat.SHOW_AS_ACTION_IF_ROOM)
        MenuItemCompat.setActionView(item, searchView)
        searchView.setOnQueryTextListener(this)
        searchView.setOnCloseListener(this)
        searchView.setOnClickListener(this)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        viewModel.getRepositories(query)
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

    override fun onClose(): Boolean {
        searchView.clearFocus()
        return true
    }

    override fun onClick(v: View?) {

    }
}