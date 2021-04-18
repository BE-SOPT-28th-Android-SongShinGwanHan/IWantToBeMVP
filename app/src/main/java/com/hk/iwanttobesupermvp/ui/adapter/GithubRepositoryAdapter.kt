package com.hk.iwanttobesupermvp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hk.iwanttobesupermvp.databinding.ItemGithubRepositoryBinding
import com.hk.iwanttobesupermvp.domain.entity.MockDataEntity
import kotlin.properties.Delegates

class GithubRepositoryAdapter :
    RecyclerView.Adapter<GithubRepositoryAdapter.GithubRepositoryViewHolder>() {

    // 이러한 형태도 있다! 라고 알아두시면 좋을거 같습니다
    // 위임 패턴 (Delegate Pattern) 이라는 것을 사용했습니다.
    var mockRepositoryList : List<MockDataEntity> by Delegates.observable(emptyList()){
        _, oldValue, newValue -> if(oldValue != newValue) notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubRepositoryViewHolder {
        val binding =
            ItemGithubRepositoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GithubRepositoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GithubRepositoryViewHolder, position: Int) {
        holder.bindWithDataBinding(mockRepositoryList[position])
    }

    override fun getItemCount(): Int = mockRepositoryList.size

    class GithubRepositoryViewHolder(
        private val binding: ItemGithubRepositoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindWithNormal(mockData: MockDataEntity) {
            binding.apply {
                itemRepositoryName.text = mockData.repositoryName
                itemRepositoryDescription.text = mockData.repositoryDescription
                itemRepositoryCoLanguage.text = mockData.userLanguage
            }
        }

        fun bindWithDataBinding(mockData: MockDataEntity) {
            binding.mockData = mockData
        }
    }
}