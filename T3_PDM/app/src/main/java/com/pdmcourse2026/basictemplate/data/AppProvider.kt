package com.pdmcourse2026.basictemplate.data

import android.content.Context
import com.pdmcourse2026.basictemplate.data.database.AppDataBase
import com.pdmcourse2026.basictemplate.data.repository.OptionRepository
import com.pdmcourse2026.basictemplate.data.repository.OptionRespositoryImpl
import com.pdmcourse2026.basictemplate.data.repository.QuestionRepository
import com.pdmcourse2026.basictemplate.data.repository.QuestionRepositoryImpl

class AppProvider(context: Context){
    private val appDataBase = AppDataBase.getDatabase(context)
    private val questionDao = appDataBase.questionDao()
    private val optionDao = appDataBase.optionDao()

    private val questionRepository: QuestionRepository =
        QuestionRepositoryImpl(questionDao)
    private val optionRepository: OptionRepository =
        OptionRespositoryImpl(optionDao)

    fun provideQuestionRepository(): QuestionRepository{
        return questionRepository
    }
    fun provideOptionRepository(): OptionRepository {
        return optionRepository
    }
}