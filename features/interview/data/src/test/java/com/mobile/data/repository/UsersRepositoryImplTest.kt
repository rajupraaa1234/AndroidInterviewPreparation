package com.mobile.data.repository

import com.interview.domain.model.UserInfoResult
import com.interview.domain.model.Users
import com.mobile.data.dto.UsersData
import com.mobile.data.network.InterviewService
import com.mobile.data.repositoryImpl.UsersRepositoryImpl
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class UsersRepositoryImplTest {

    private val apiService: InterviewService = mockk()
    private lateinit var repository: UsersRepositoryImpl

    @Before
    fun setUp() {
        repository = UsersRepositoryImpl(apiService = apiService)
    }

    @Test
    fun `GIVEN repository WHEN getUsers invoke THEN return correct success response`() = runTest {
        coEvery {
            apiService.getUsers()
        } returns Response.success(
            listOf(
                UsersData(
                    id = 1,
                    name = "name",
                    company = "name",
                    username = "name",
                    email = "name",
                    address = "name",
                    zip = "name",
                    state = "name",
                    country = "name",
                    phone = "name",
                    photo = "name"
                )
            )
        )

        val actual = repository.getUsers()

        val expected = UserInfoResult.Success(
            users = listOf(
                Users(
                    id = 1,
                    name = "name",
                    company = "name",
                    username = "name",
                    email = "name",
                    address = "name",
                    zip = "name",
                    state = "name",
                    country = "name",
                    phone = "name",
                    photo = "name"
                )
            )
        )
        assertEquals(expected, actual)
    }
}