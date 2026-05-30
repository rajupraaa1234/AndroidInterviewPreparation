package com.interview.ui.state

data class UserState(
    val userList: List<UserInfo> = emptyList(),
    val isError: Boolean = false
)

data class UserInfo(
    val name: String = "",
    val company: String = "",
    val username: String = "",
    val email: String = "",
)
