package com.pdmcourse2026.basictemplate.data.api.Option

import kotlinx.serialization.Serializable

@Serializable
data class CreatePostDTO(
    val optionId: Int
)