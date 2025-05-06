package org.core.presentation.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import org.cmparchitecture.navigation.NavigationAction
import org.cmparchitecture.utils.extensions.vmScopeMain

abstract class BaseViewModel : ViewModel() {

    private val _baseUIEvents = MutableSharedFlow<BaseViewModelEvents>()
    val baseUIEvents = _baseUIEvents.asSharedFlow()

    protected fun showError(msg: String) {
        vmScopeMain {
            hideLoader()
            _baseUIEvents.emit(BaseViewModelEvents.ShowError(msg))
        }
    }

    protected fun showMsg(msg: String) {
        vmScopeMain {
            hideLoader()
            _baseUIEvents.emit(BaseViewModelEvents.ShowError(msg))
        }
    }

    protected fun showToast(msg: String) {
        vmScopeMain {
            _baseUIEvents.emit(BaseViewModelEvents.ShowToast(msg))
        }
    }

    protected fun navigate(route: NavigationAction) = vmScopeMain {
        hideLoader()
        _baseUIEvents.emit(BaseViewModelEvents.Navigate(route))
    }

    protected fun navigateBack() {
        navigate(NavigationAction.PopBackStack)
    }

    protected fun showLoader() = vmScopeMain {
        _baseUIEvents.emit(BaseViewModelEvents.ShowLoader(true))
    }

    protected fun hideLoader() = vmScopeMain {
        _baseUIEvents.emit(BaseViewModelEvents.ShowLoader(false))
    }

    sealed class BaseViewModelEvents {
        data class Navigate(val route: NavigationAction) : BaseViewModelEvents()
        data class ShowError(val msg: String) : BaseViewModelEvents()
        data class ShowToast(val msg: String) : BaseViewModelEvents()
        data class ShowLoader(val show: Boolean) : BaseViewModelEvents()
    }
}

