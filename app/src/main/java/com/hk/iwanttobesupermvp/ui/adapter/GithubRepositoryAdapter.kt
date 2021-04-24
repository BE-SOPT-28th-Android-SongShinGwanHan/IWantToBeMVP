package com.hk.iwanttobesupermvp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hk.iwanttobesupermvp.databinding.ItemGithubRepositoryBinding
import com.hk.iwanttobesupermvp.domain.entity.MockDataEntity
import kotlin.properties.Delegates
import kotlin.properties.ObservableProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class GithubRepositoryAdapter :
    RecyclerView.Adapter<GithubRepositoryAdapter.GithubRepositoryViewHolder>() {

    // 코드리뷰 X
    // delegate 패턴
    var mockRepositoryList: MutableList<MockDataEntity> by Delegates.observable(mutableListOf()) { _, oldValue, newValue ->
        if (oldValue != newValue) notifyDataSetChanged()
    }
    /*var mockRepositoryList : MutableList<MockDataEntity> = mutableListOf()*/

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

    fun moveItem(from: Int, to: Int): Boolean {
        val tempData = mockRepositoryList[from]
        mockRepositoryList.removeAt(from)
        mockRepositoryList.add(to, tempData)
        notifyItemMoved(from, to)
        notifyItemChanged(from, to)
        return true
    }

    fun removeItem(position: Int) {
        mockRepositoryList.removeAt(position)
        notifyItemRemoved(position)
    }
}

/**
 * 기존 Delegate.observable의 함수원형을 그대로 따와 이름만 바꾼 형태로 만든 것입니다.
 * 제가 만든게 아닙니다 (공부자료, 코드리뷰 X)
 */
inline fun <T> adapterDataChangeProp(
    initialValue: T,
    crossinline onChange: (property: KProperty<*>, oldValue: T, newValue: T) -> Unit
): ReadWriteProperty<Any?, T> = object : ObservableProperty<T>(initialValue) {
    override fun afterChange(property: KProperty<*>, oldValue: T, newValue: T) =
        onChange(property, oldValue, newValue)
}