package study.android.bodyprofile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    private val _title = MutableLiveData<String>()
    val title: LiveData<String>
        get() = _title

    private val _description = MutableLiveData<String>()
    val description: LiveData<String>
        get() = _description

    private val _memoList = MutableLiveData<List<String>>()
    val memoList: LiveData<List<String>>
        get() = _memoList

    init {
        _title.value = "Title in ViewModel"
        _description.value = "Description in ViewModel"
        _memoList.value = listOf()
    }

    fun setTitle(title: String) {
        _title.value = title
    }

    fun setDescription(title: String) {
        _description.value = title
    }

    fun setMemo(title: List<String>) {
        _memoList.value = title
    }
}