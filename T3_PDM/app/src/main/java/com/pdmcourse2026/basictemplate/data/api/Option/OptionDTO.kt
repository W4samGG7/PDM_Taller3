package com.pdmcourse2026.basictemplate.data.api.Option

import com.pdmcourse2026.basictemplate.model.Option
import kotlinx.serialization.Serializable

@Serializable
data class OptionDTO(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val votes: Int
)

fun OptionDTO.toModel() : Option {
    return Option(
        id = id,
        name = name,
        imageUrl = imageUrl,
        votes = votes
    )
}