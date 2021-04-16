package com.hk.iwanttobesupermvp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hk.iwanttobesupermvp.databinding.ItemGithubRepositoryBinding
import com.hk.iwanttobesupermvp.domain.entity.MockDataEntity

class GithubRepositoryAdapter :
    RecyclerView.Adapter<GithubRepositoryAdapter.GithubRepositoryViewHolder>() {

    val mockRepositoryList = mutableListOf<MockDataEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubRepositoryViewHolder {
        val binding =
            ItemGithubRepositoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GithubRepositoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GithubRepositoryViewHolder, position: Int) {
        holder.bindWithNormal(mockRepositoryList[position])
    }

    override fun getItemCount(): Int = mockRepositoryList.size

    class GithubRepositoryViewHolder(
        private val binding: ItemGithubRepositoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindWithNormal(mockData : MockDataEntity) {
            binding.apply {
                itemRepositoryName.text = mockData.repositoryName
                itemRepositoryDescription.text = mockData.repositoryDescription
                itemRepositoryCoLanguage.text = mockData.userLanguage
            }
        }

        fun bindWithDataBinding(mockData : MockDataEntity) {
            binding.mockData = mockData
        }
    }
}