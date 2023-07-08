package com.example.pfe1.register.data

import com.example.pfe1.enumClass.Subjects
import com.example.pfe1.enumClass.UserType
import com.example.pfe1.questions.data.QuestionRemote
import com.example.pfe1.questions.domain.model.Question
import com.example.pfe1.register.domain.User

data class UserRemote(
    //common
    val id : String = "",
    var name : String = "",
    val email : String = "",
    val userType : UserType = UserType.DEFAULT,
    val schoolId : String? = null,
    val classId: String? = null,
    val schoolIdList : List<String> = emptyList(),
    val classIdList : List<String> =  emptyList(),
    //prof



) {
   /* fun toUser(): User {
        return when (userType) {
            UserType.PARENT -> User.Parent(
                id = id,
                name = name,
                email = email,

            )

            UserType.SCHOOL -> User.School(
                id = id,
                name = name,
                email = email,
            )

            UserType.TEACHER -> User.Teacher(
                id = id,
                name = name,
                email = email,
            )

            else -> User.Unknown(
                id = id,
                name = name,
                email = email,
            )
        }
    }*/

    companion object {
        fun fromUser(user: User): UserRemote {
            return when (user) {
                is User.Parent -> UserRemote(
                    id = user.id,
                    email = user.email,
                    name = user.name,
                    userType = UserType.PARENT,
                )

                is User.School -> UserRemote(
                    id = user.id,
                    email = user.email,
                    name = user.name,
                    userType = UserType.SCHOOL,
                )

                is User.Teacher -> UserRemote(
                    id = user.id,
                    email = user.email,
                    name = user.name,
                    userType = UserType.TEACHER,
                )

                is User.Unknown -> UserRemote(
                    id = user.id,
                    email = user.email,
                    name = user.name,
                    userType = UserType.DEFAULT,
                )
            }
        }
    }

    data class Teacher(
        val subject : Subjects = Subjects.DEFAULT,
    )
}
