package com.pdmcourse2026.basictemplate.data.repository

import com.pdmcourse2026.basictemplate.data.database.dao.OptionDao
import com.pdmcourse2026.basictemplate.data.database.entities.toModel
import com.pdmcourse2026.basictemplate.model.Option
import com.pdmcourse2026.basictemplate.model.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class OptionRespositoryImpl (
    private val optionDao: OptionDao
) : OptionRepository {
    override fun getOptions(questionId: Int): Flow<List<Option>> {
        return optionDao.getAllOptions(questionId).map { entities ->
            entities.map { it.toModel() }
        }
    }

    override suspend fun addOption(name: String, imageUrl: String, questionId: Int) {
        val option = Option(name = name, imageUrl = imageUrl, questionId = questionId, votes = 0)
        optionDao.insertOption(option.toEntity())
    }

    override suspend fun deleteOption(option: Option) {
        optionDao.deleteOption(option.toEntity())
    }

}