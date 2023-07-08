package com.example.pfe1.enumClass

import com.example.pfe1.kidsView.domain.model.Child
import com.example.pfe1.questions.domain.model.Question
import io.grpc.Deadline
import java.util.Date

enum class UserType {
    DEFAULT,
    TEACHER,
    PARENT,
    SCHOOL,
}

/*data class Teacher(
    val id : String,
    val name : String,
    val classesId : List<String>,
    val schoolsId : List<String>,
    val subjects : List<Subjects>

)

data class Class(
    val name : String,
    val schoolId : String,
    val grade : SchoolYear

)
data class Child(
    val id: String,
    val parentId: String,
    val name: String,
    val week: Int,
    val grade: SchoolYear,
    val classId: String,
    val schoolId : String,
    val avatarUrl : String,
    val score: Int
)

data class Parent(
    val id: String,
    val name: String,
)

data class Task(
    val id: String,
    val teacherId: String,
    val classId: String,
    val deadline: Date,
    val grade: SchoolYear,
    val subject: Subjects,
    val chapterId: String,
    val weekNumber: Int,

)

data class TaskStats(
    val id: String,
    val taskId: String,
    val childId: String,
    val score: Int,
    val isEntryLevelDone: Boolean
)

data class Subject(
    val id: String,
    val grade: SchoolYear,
    val name: String,
)

data class School(
    val id: String,
    val name: String,
)

data class Chapter(
    val id: String,
    val name: String,
    val subject: Subjects,
    val grade: SchoolYear,
    val Title: String,
    val iconUrl: String,
    val type: Chapters,
    val subjectid: String,
    val numberOfLevels: Int,
)

//Competition:
data class ChildCompetitionStats(
     val id: String,
     val finished: Boolean,
     val score: Int,
)

data class Vocabulary(
    val id: String,
    val chapterId: String,
    val iconUrl: String,
    val name: String
)

data class trainingZoneStats(
    val id: String,
    val childId: String,
    val chapterId: String,
    val level: Int,
)

/* fekra w barra L godem */
data class QuetionOfSubject(
    val id: String,
    val chapterId: String,
    val type: QuestionType,




)





//////////////////////////
data class QuizOfIcons(
    val question: String,
    val iconsUrl: List<String>,
    val correctAnsware: String
) : Question

data class QuizVocabulary(
    val iconUrl: String,
    val answersUrl: List<String>
) : Question

data class SpealingBeginner(
    val question: String,
    val iconsUrl: List<String>
) : Question

data class SpealingAdvanced(
    val question: String,
    val iconsUrl: List<String>
) : Question

data class AdditionStandar(
    val question: String,
    val anszers: List<String>,
) : Question

data class AdditionMisses(
    val question1: String,
    val question2: String,
    val anszersUrl: List<String>,
) : Question

data class AdditionDomino(
    val dominoUrl1: String,
    val dominoUrl2: String,
    val anszers: List<String>,
) : Question*/


