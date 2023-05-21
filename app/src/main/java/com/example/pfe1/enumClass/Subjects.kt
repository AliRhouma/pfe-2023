package com.example.pfe1.enumClass

enum class Subjects {
    DEFAULT,
    MATHS,
    ENGLISH,
    SCIENCE,
    HISTORY,
    GEOGRAPHY
}

fun toSubjects(s: String): Subjects {
    return when (s) {
        "MATHS" -> Subjects.MATHS
        "ENGLISH" -> Subjects.ENGLISH
        "SCIENCE" -> Subjects.SCIENCE
        "HISTORY" -> Subjects.HISTORY
        "GEOGRAPHY" -> Subjects.GEOGRAPHY
        else -> Subjects.DEFAULT
    }
}
