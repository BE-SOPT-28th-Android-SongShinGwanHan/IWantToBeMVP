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

    // 이러한 형태도 있다! 라고 알아두시면 좋을거 같습니다
    // 위임 패턴 (Delegate Pattern) 이라는 것을 사용했습니다.
    var mockRepositoryList: List<MockDataEntity> by adapterDataChangeProp(emptyList()) { _, oldValue, newValue ->
        if (oldValue != newValue) notifyDataSetChanged()
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

/**
 * 기존 Delegate.observable의 함수원형을 그대로 따와 이름만 바꾼 형태로 만든 것입니다.
 * 제가 만든게 아닙니다 (공부자료, 코드리뷰 X)
 *
 * inline 함수에 제네릭 함수인 이녀석은...
 * 매개변수로 초기 값인 initialValue와 crossinline 함수인 onChange를 받는다.
 * 반환값으로 ReadWriteProperty를 반환한다.
 *
 * 그렇다면 ReadWriteProperty는 무엇이냐?
 * Any? 와 T를 받아서
 * T에 Any? 값을 get or set해주는 interface다.
 * 위임은 이곳에서 일어난다고 봐야한다.
 *
 * 그렇다면 반환 값은 ReadWriteProperty인 것을 확인헀다.
 *
 * 그럼 이 함수의 로직을 살펴보자
 * object : ObservableProperty<T>(initialValue {람다}
 * 이와 같은 형태를 띄웠는데 이는 ObservableProperty형태의 익명함수를 반환한다는 얘기이다.
 * 그렇다면 ObservableProperty는 어떻게 생겼을까?
 *
 * public abstract class ObservableProperty<V>(initialValue: V) : ReadWriteProperty<Any?, V> {
     private var value = initialValue
     protected open fun beforeChange(property: KProperty<*>, oldValue: V, newValue: V): Boolean = true

     protected open fun afterChange(property: KProperty<*>, oldValue: V, newValue: V): Unit {}

     public override fun getValue(thisRef: Any?, property: KProperty<*>): V {
         return value
     }

     public override fun setValue(thisRef: Any?, property: KProperty<*>, value: V) {
         val oldValue = this.value
         if (!beforeChange(property, oldValue, value)) {
            return
         }
         this.value = value
         afterChange(property, oldValue, value)
     }
}
 */
inline fun <T> adapterDataChangeProp(
    initialValue: T,
    crossinline onChange: (property: KProperty<*>, oldValue: T, newValue: T) -> Unit
): ReadWriteProperty<Any?,T> = object : ObservableProperty<T>(initialValue){
    override fun afterChange(property: KProperty<*>, oldValue: T, newValue: T) = onChange(property, oldValue, newValue)
}