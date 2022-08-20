package com.owow.characterapp.ui.adapter

import android.view.View

interface OnDataBindCallback<V> {
    fun onItemClick(view: View, position: Int, v: V)
    fun onDataBind(v: V, onClickListener: View.OnClickListener)
}