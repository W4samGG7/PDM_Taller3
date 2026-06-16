package com.pdmcourse2026.basictemplate.data.repositories.OptionRepository

import com.pdmcourse2026.basictemplate.data.api.KtorClient
import com.pdmcourse2026.basictemplate.data.api.Option.CreatePostDTO
import com.pdmcourse2026.basictemplate.data.api.Option.ErrorDTO
import com.pdmcourse2026.basictemplate.data.api.Option.OptionDTO
import com.pdmcourse2026.basictemplate.data.api.Option.toModel
import com.pdmcourse2026.basictemplate.model.Option
import com.pdmcourse2026.basictemplate.model.Error
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class OptionApiRepository : OptionRepository {
    override suspend fun getOption(): Result<List<Option>> {
        try {
            val response: List<OptionDTO> = KtorClient.client.get("options").body()

            return Result.success(response.map { optionDTO -> optionDTO.toModel() })
        }catch (e: Exception){
            return Result.failure(e)
        }
    }

    override suspend fun vote(optionId: Int): Result<Error> {
        try {
            val request = CreatePostDTO(
                optionId = optionId
            )

            val response: ErrorDTO = KtorClient.client.post("vote") {
                contentType(ContentType.Application.Json)
                setBody(request)
            }.body()

            return Result.success(response.toModel())
        }catch (e: Exception){
            return Result.failure(e)
        }
    }

}