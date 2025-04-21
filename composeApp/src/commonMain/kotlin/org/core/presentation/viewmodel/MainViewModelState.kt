package org.core.presentation.viewmodel

import org.core.presentation.base.BaseState


data class MainViewModelState(
    val isBottomNavVisible: Boolean = false,
    val isFabBtnVisible: Boolean = false
) : BaseState