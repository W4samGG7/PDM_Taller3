package com.pdmcourse2026.basictemplate.data.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.pdmcourse2026.basictemplate.model.Option

@Entity(
    tableName = "options",
    foreignKeys = [
        ForeignKey(
            entity = QuestionEntity::class,
            parentColumns = ["id"],
            childColumns = ["questionId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("questionId")]
)

data class OptionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val imageUrl: String,
    val votes: Int,
    val questionId: Int
)

fun OptionEntity.toModel(): Option {
    return Option(
        id = id,
        name = name,
        imageUrl = imageUrl,
        votes = votes,
        questionId = questionId
    )
}
