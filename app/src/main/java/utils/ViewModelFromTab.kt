package utils

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import screens.logged.home.HomeViewModel

abstract class ViewModelFromTab(application: android.app.Application): AndroidViewModel(application) {
    val hasInit: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }
}