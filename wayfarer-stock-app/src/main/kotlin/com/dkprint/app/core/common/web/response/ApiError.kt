package com.dkprint.app.core.common.web.response

data class ApiError(val element: ApiErrorElement) {
    companion object {
        fun of(element: ApiErrorElement): ApiError = ApiError(element)
    }
}
