package com.owow.characterapp.ui.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel

abstract class BaseViewModel<N>(application: Application) : AndroidViewModel(application) {
    private val mApplication: Application = application
    private var mNavigator: N? = null

    fun getNavigator(): N {
        return mNavigator!!
    }

    fun setNavigator(navigator: N) {
        this.mNavigator = navigator
    }
}