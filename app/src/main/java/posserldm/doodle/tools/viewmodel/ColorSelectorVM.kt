package posserldm.doodle.tools.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ColorSelectorVM: ViewModel() {
    private val _color = MutableLiveData<String>()
    val color: LiveData<String>
        get() = _color

    fun updateValue(value: String) {
        _color.value = value
    }
}