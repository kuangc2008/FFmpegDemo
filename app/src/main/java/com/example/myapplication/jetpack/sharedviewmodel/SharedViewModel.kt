package com.example.myapplication.jetpack.sharedviewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel: ViewModel(){
    val inputNumber = MutableLiveData<Int>()
}