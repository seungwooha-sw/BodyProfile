package study.android.bodyprofile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import study.android.bodyprofile.api.model.SearchNewsResponse
import study.android.bodyprofile.api.model.TransferPapagoResponse

class MainViewModel: ViewModel(){
    private var naverRetrofitManager: RetrofitManager = RetrofitManager.createRetrofitForNaverApi()

    var newsResponse = MutableLiveData<SearchNewsResponse>()
    var papagoResponse = MutableLiveData<TransferPapagoResponse>()

    fun test() {
        viewModelScope.launch {
            // TODO  get error :
            //  java.lang.IllegalArgumentException: Unable to create call adapter for class java.lang.Object
            //  for method NaverAPI.getSearchNews
            naverRetrofitManager.testNews()
            delay(1000)
            naverRetrofitManager.testPapago()
        }
    }
}