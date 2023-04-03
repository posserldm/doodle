package posserldm.doodle.tools.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TypeSelectorVM: ViewModel() {
    private val _type = MutableLiveData<String>()

    val type: LiveData<String>
        get() = _type

    fun updateValue(value: String) {
        _type.value = value
    }
}