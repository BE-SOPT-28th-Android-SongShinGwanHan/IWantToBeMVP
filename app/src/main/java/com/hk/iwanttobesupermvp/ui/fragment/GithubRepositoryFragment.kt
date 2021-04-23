package com.hk.iwanttobesupermvp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hk.iwanttobesupermvp.R
import com.hk.iwanttobesupermvp.contract.fragment.githubrepository.GithubRepositoryFragmentContract
import com.hk.iwanttobesupermvp.databinding.FragmentGithubRepositoryBinding
import com.hk.iwanttobesupermvp.domain.entity.MockDataEntity
import com.hk.iwanttobesupermvp.presenter.githubrepository.GithubRepositoryPresenter
import com.hk.iwanttobesupermvp.ui.adapter.GithubRepositoryAdapter
import com.hk.iwanttobesupermvp.util.FragmentBindingAutoCleared
import com.hk.iwanttobesupermvp.util.getItemTouchHelper
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class GithubRepositoryFragment @Inject constructor() : Fragment(),
    GithubRepositoryFragmentContract.GithubRepositoryView {

    private var binding: FragmentGithubRepositoryBinding by FragmentBindingAutoCleared()

    private lateinit var githubRepositoryAdapter: GithubRepositoryAdapter

    @Inject
    lateinit var githubRepositoryPresenter: GithubRepositoryPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_github_repository,
            container,
            false
        )
        binding.githubRepositoryPresenter = githubRepositoryPresenter
        githubRepositoryAdapter = GithubRepositoryAdapter()
        githubRepositoryPresenter.setRecyclerView()

        // use retrofit2.Call and enqueue
        /*githubRepositoryPresenter.fetchMockDataWithCall()*/

        // use Coroutine
        githubRepositoryPresenter.fetchMockDataWithCoroutine()

        // use RxJava
        /*githubRepositoryPresenter.fetchMockDataWithRxJava()*/

        return binding.root
    }

    override fun setGithubRepositoryAdapter(mockDataList: MutableList<MockDataEntity>) {
        githubRepositoryAdapter.apply {
            mockRepositoryList = mockDataList
            notifyDataSetChanged()
        }
    }

    override fun initializeRecyclerView() {
        binding.fragmentGithubRepositoryRecyclerView.apply {
            getItemTouchHelper().attachToRecyclerView(this)
            adapter = githubRepositoryAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            makeItemTouchHelper().attachToRecyclerView(this)
        }
    }

    override fun changeLayoutManager() {
        binding.fragmentGithubRepositoryRecyclerView.apply {
            adapter = githubRepositoryAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
            setHasFixedSize(true)
        }
    }

    private fun makeItemTouchHelper() : ItemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
        ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.START or ItemTouchHelper.END,ItemTouchHelper.START or ItemTouchHelper.END) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            val from = viewHolder.adapterPosition
            val to = target.adapterPosition

            return githubRepositoryAdapter.moveItem(from,to)
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            githubRepositoryAdapter.removeItem(viewHolder.adapterPosition)
        }

        override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
            super.onSelectedChanged(viewHolder, actionState)

            if (actionState == ItemTouchHelper.ACTION_STATE_DRAG) {
                viewHolder?.itemView?.alpha = 0.5f
            }
        }

        override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
            super.clearView(recyclerView, viewHolder)

            viewHolder.itemView.alpha = 1.0f
        }
    })
}