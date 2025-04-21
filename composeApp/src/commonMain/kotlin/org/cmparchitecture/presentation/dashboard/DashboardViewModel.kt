package org.cmparchitecture.presentation.dashboard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import org.core.presentation.base.BaseViewModel

class DashboardViewModel() : BaseViewModel() {
    var state by mutableStateOf(DashboardScreenState())
        private set
}