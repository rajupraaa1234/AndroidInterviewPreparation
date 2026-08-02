package com.mobile.ui.model

class Person(
    val firstName: String,
    val secondName: String
) {
    fun doesMatchSearchQuery(query: String): Boolean {
        val matchingCombination = listOf(
            "$firstName$secondName",
            "$firstName $secondName",
            "${firstName.first()} ${secondName.first()}",
        )
        return matchingCombination.any {
            it.contains(query, ignoreCase = true)
        }
    }
}

internal fun getPerson() : List<Person> {
   return listOf(
        Person(
            firstName = "Raju",
            secondName = "Kumar"
        ),
        Person(
            firstName = "Rahul",
            secondName = "Kumar"
        ),
        Person(
            firstName = "Pintu",
            secondName = "Kumar"
        ),
        Person(
            firstName = "Gaurav",
            secondName = "Gupta"
        ),
        Person(
            firstName = "Sanju",
            secondName = "Devi"
        ),
        Person(
            firstName = "Pankaj",
            secondName = "Yadav"
        )
    )
}