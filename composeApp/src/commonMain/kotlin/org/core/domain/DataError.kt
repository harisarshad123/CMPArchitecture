package org.core.domain

sealed interface DataError : Error {
    enum class Remote : DataError {
        REQUEST_TIMEOUT,
        TOO_MANY_REQUESTS,
        NO_INTERNET,
        SERVER,
        SERIALIZATION,
        UNKNOWN,
    }

    sealed class TransferMessage : DataError {
        data class ErrorMessage(val msg: String) : TransferMessage()
    }

    enum class Local : DataError {
        DISK_FULL,
        UNKNOWN
    }
}