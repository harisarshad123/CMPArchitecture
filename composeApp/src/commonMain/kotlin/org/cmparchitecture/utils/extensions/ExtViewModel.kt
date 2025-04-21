package org.cmparchitecture.utils.extensions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

fun ViewModel.vmScope(block: suspend CoroutineScope.() -> Unit): Job {
    return viewModelScope.launch(Dispatchers.Default) {
        block()
    }
}

fun ViewModel.vmScopeDefault(block: suspend CoroutineScope.() -> Unit): Job {
    return viewModelScope.launch {
        block()
    }
}

fun ViewModel.vmScopeMain(block: suspend CoroutineScope.() -> Unit): Job {
    return viewModelScope.launch(Dispatchers.Main) {
        block()
    }
}