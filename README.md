# IWantToBeMVP

## Preview

<div>
    <img src="https://github.com/BE-SOPT-28th-Android-SongShinGwanHan/IWantToBeMVP/blob/hunki/first_assignment/1%EC%B0%A8%EA%B3%BC%EC%A0%9C.gif" width="300" height="650"/>
    <img src="https://github.com/BE-SOPT-28th-Android-SongShinGwanHan/IWantToBeMVP/blob/hunki/second_assignment/2%EC%B0%A8%EA%B3%BC%EC%A0%9C.gif" width="300" height="650"/>
</div>



## 내용

### 1주차 STEP1

```kotlin
<SignInPresenter>
class SignInPresenter(
    private var signInView: SignInContract.SignInView?,
    private val signInModel: SignInContract.SignInModel
) : SignInContract.SignInPresenter {
    override fun onLoginButtonClick() {
        signInView?.getUserStatus()?.let { signInModel.setUserInfo(it) }
        if (signInModel.isValidate()) {
            signInView?.navigateToHome()
        } else {
            signInView?.showToast(message = "아이디 혹은 비밀번호를 입력하세요.")
        }
    }

    override fun onSignUpTextClick() {
        signInView?.navigateToSignUpPage()
    }

    override fun onDestroy() {
        signInView = null
    }

}
<SignInActivity>
override fun onDestroy() {
        super.onDestroy()
        signInPresenter.onDestroy()
    }

    override fun getUserStatus(): User = User(
        binding.signInIdEditText.text.toString(),
        binding.signInPasswordEditText.text.toString()
    )

    override fun navigateToHome() {
        val intent = Intent(this, HomeActivity::class.java)
        intent.putExtra("id", binding.signInIdEditText.text.toString())
        startActivity(intent)
        finish()
    }

    override fun navigateToSignUpPage() {
        // startActivityForResult 이거 바뀐거 사용해야 해서 차차
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun showToast(message: String) {
        shortToast(message)
    }
기존의 것들도 전부 MVP패턴을 사용해 구현을 하였고 Presenter에 있는 interface를 구현함으로써 뷰와 모델간의 중재자 역할을 수행할 수 있습니다.
```

<div>
    <img src="https://github.com/BE-SOPT-28th-Android-SongShinGwanHan/IWantToBeMVP/blob/hunki/first_assignment/%EC%83%9D%EB%AA%85%EC%A3%BC%EA%B8%B0%20%EB%A1%9C%EA%B7%B8.PNG" width="300" height="300"
</div>
---------------------------------------------------------------------



### 2주차 STEP

```kotlin
<githubRepositoryAdapter>
class GithubRepositoryAdapter :
    RecyclerView.Adapter<GithubRepositoryAdapter.GithubRepositoryViewHolder>() {

    // 코드리뷰 X
    // delegate 패턴
/*    var mockRepositoryList: MutableList<MockDataEntity> by Delegates.observable(mutableListOf()) { _, oldValue, newValue ->
        if (oldValue != newValue) notifyDataSetChanged()
    }*/
    var mockRepositoryList : MutableList<MockDataEntity> = mutableListOf()

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
Databinding을 사용할때와 일반적으로 사용했을때 시간 비교를 위해 bind해주는 메소드를 2개 만들었었고 , 선택과제를 위해 moveItem , removeItem을 만들어서 사용했습니다.

<githubRepositoryFragment>
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

프라그먼트에서 주도록 하였습니다.(추후 리팩토링 할 예정)
```





