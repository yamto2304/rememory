package com.sunasterisk.rememory.data.source.base

import java.lang.Exception

interface OnDataLoadedCallback<T> {
    fun onSuccess(data: T)
    fun onFailure(exception: Exception)
}
