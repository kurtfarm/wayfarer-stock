package com.dkprint.app.core.web

import org.springframework.data.domain.Page

data class PagingResult<T>(
    val content: List<T>,
    val totalPages: Int,
    val isLast: Boolean,
) {
    companion object {
        fun <T> from(page: Page<T>): PagingResult<T> =
            PagingResult(
                content = page.content,
                totalPages = page.totalPages,
                isLast = page.isLast
            )
    }
}
