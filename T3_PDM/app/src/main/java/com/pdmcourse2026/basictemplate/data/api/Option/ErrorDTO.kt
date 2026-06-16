package com.pdmcourse2026.basictemplate.data.api.Option

import com.pdmcourse2026.basictemplate.model.Option
import com.pdmcourse2026.basictemplate.model.Error
import kotlinx.serialization.Serializable

@Serializable
data class ErrorDTO(
    val ok: Boolean,
    val message: String
)

fun ErrorDTO.toModel() : Error {
    return Error(
        ok = ok,
        message = message
    )
}