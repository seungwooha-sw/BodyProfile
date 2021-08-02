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
            newsResponse.value = naverRetrofitManager.testNews()
            delay(1000)
            // papagoResponse.value = naverRetrofitManager.testPapago()
        }
    }
}