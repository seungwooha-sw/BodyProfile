package study.android.bodyprofile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import study.android.bodyprofile.api.model.SearchNewsResponse
import study.android.bodyprofile.api.model.TransferPapagoResponse

class MainViewModel: ViewModel() {

    private var naverRetrofitManager: RetrofitManager = RetrofitManager.createRetrofitForNaverApi()

    var newsResponse = MutableLiveData<SearchNewsResponse>()
    var papagoResponse = MutableLiveData<TransferPapagoResponse>()

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

    fun test() {
        viewModelScope.launch {
            newsResponse.value = naverRetrofitManager.testNews()
            delay(1000)
            // papagoResponse.value = naverRetrofitManager.testPapago()
        }
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
