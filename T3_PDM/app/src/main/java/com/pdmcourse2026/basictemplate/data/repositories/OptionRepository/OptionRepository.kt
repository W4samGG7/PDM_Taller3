package com.pdmcourse2026.basictemplate.data.repositories.OptionRepository

import com.pdmcourse2026.basictemplate.model.Error
import com.pdmcourse2026.basictemplate.model.Option

interface OptionRepository {
    suspend fun getOption() : Result<List<Option>>

    suspend fun vote(optionId: Int) : Result<Error>
}