package com.dkprint.app.core.common.web.response

data class ApiErrorCode(val value: String) {
    companion object {
        fun of(code: String) = ApiErrorCode(code)
    }
}
