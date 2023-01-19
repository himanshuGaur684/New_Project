package com.example.newproject.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newproject.common.Resource
import com.example.newproject.repository.MainRepository
import com.example.newproject.room.model.Area
import com.example.newproject.room.model.Country
import com.example.newproject.room.model.Region
import com.example.newproject.room.model.Zone
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {


    private val _country = MutableStateFlow<List<Country>>(emptyList())
    val country: StateFlow<List<Country>> = _country

    private val _zone = MutableStateFlow<List<Zone>>(emptyList())
    val zone: StateFlow<List<Zone>> = _zone

    private val _region = MutableStateFlow<List<Region>>(emptyList())
    val region: StateFlow<List<Region>> = _region

    private val _area = MutableStateFlow<List<Area>>(emptyList())
    val area: StateFlow<List<Area>> = _area

    private val _filterArea = MutableStateFlow<List<Area>>(emptyList())
    val filterArea: StateFlow<List<Area>> = _filterArea

    private val query = MutableStateFlow<String>("")
    fun setQuery(s: String) {
        query.value = s
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val result = async { mainRepository.getApiResponse() }.await()
            if (result is Resource.Success) {
                getCountryList()
            }
        }
        viewModelScope.launch {
            query.debounce(1000).collectLatest {
                searchArea(it)
            }
        }
    }

    fun getRegionList(s: String) = viewModelScope.launch(Dispatchers.IO) {
        _region.value = mainRepository.getRegionList(s)
    }

    fun getAreaList(s: String) = viewModelScope.launch(Dispatchers.IO) {
        _area.value = mainRepository.getAreaList(s)
        _filterArea.value = _area.value
    }

    private fun getCountryList() = viewModelScope.launch(Dispatchers.IO) {
        _country.value = mainRepository.getCountryList()
    }

     fun getZoneList(s:String) = viewModelScope.launch(Dispatchers.IO) {
        _zone.value = mainRepository.getZoneList(s)
    }

    private fun searchArea(s: String) {
        _filterArea.value = _area.value.filter { it.area.lowercase().contains(s) }
    }

    fun resetFilterList(){
        _filterArea.value = _area.value
    }

}