package com.example.pfe1.enumClass

enum class SchoolYear {
    DEFAULT,
    GRADE_1,
    GRADE_2,
    GRADE_3,
    GRADE_4,
    GRADE_5,
    GRADE_6
}

fun toSchoolYear(s: String): SchoolYear {
    return when (s) {
        "GRADE_1" -> SchoolYear.GRADE_1
        "GRADE_2" -> SchoolYear.GRADE_2
        "GRADE_3" -> SchoolYear.GRADE_3
        "GRADE_4" -> SchoolYear.GRADE_4
        "GRADE_5" -> SchoolYear.GRADE_5
        "GRADE_6" -> SchoolYear.GRADE_6
        else -> SchoolYear.DEFAULT
    }
}

