package posserldm.doodle.tools.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WidthSelectorVM: ViewModel() {
    private val _width = MutableLiveData<Float>()

    val width: LiveData<Float>
        get() = _width

    fun updateValue(value: Float) {
        _width.value = value
    }
}