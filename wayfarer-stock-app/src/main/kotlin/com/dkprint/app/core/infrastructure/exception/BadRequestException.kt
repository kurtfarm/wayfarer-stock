package com.dkprint.app.core.infrastructure.exception

import com.dkprint.app.core.common.web.response.ErrorCodeResolvingApiErrorException
import com.dkprint.app.core.common.web.response.ExtendedHttpStatus


class BadRequestException(code: String = "bad-request", cause: Throwable? = null) :
    ErrorCodeResolvingApiErrorException(ExtendedHttpStatus.BAD_REQUEST, code, cause)
