package com.example.weatherapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.Constant
import com.example.api.NetworkResponse
import com.example.api.RetrofitInstance
import com.example.api.WeatherModel
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {


    private val weatherApi = RetrofitInstance.weatherApi
    private val _weatherResult = MutableLiveData<NetworkResponse<WeatherModel>>()
    val weatherResult : LiveData<NetworkResponse<WeatherModel>> = _weatherResult

    fun getData( city :String){
        _weatherResult.value = NetworkResponse.Loading
        viewModelScope.launch{
            try{
                val response = weatherApi.getWeater(Constant.apikey,city)
                if(response.isSuccessful){

                    response.body()?.let{
                        _weatherResult.value = NetworkResponse.Success(it)
                    }

                }else{
                    _weatherResult.value = NetworkResponse.Error("Failed to load data")
                }
            }
            catch( e : Exception){
                _weatherResult.value = NetworkResponse.Error("Failed to load data")
            }
        }

    }

}

