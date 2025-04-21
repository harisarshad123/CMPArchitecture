package org.core.presentation.base

import org.cmparchitecture.Platform
import org.cmparchitecture.getPlatform

interface BaseState {
    val isLoading: Boolean
        get() = false
    val isMainButtonEnable: Boolean
        get() = true

    val platform: Platform
        get() = getPlatform()
}