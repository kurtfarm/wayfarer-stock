package com.dkprint.app.core.common.web.response

open class ApiErrorException(
    val error: ApiError,
    cause: Throwable? = null,
) : RuntimeException(cause)
