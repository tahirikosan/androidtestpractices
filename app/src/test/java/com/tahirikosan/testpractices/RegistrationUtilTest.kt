package com.tahirikosan.testpractices

import com.google.common.truth.Truth.assertThat
import com.tahirikosan.testpractices.exercise.RegistrationUtil
import org.junit.Test

// Unit test examples.
class RegistrationUtilTest{

    @Test
    fun `empty username returns false`(){
        val result = RegistrationUtil.validateRegistrationInput(
            "",
            "123456",
            "123456"
        )

        assertThat(result).isFalse()
    }

    @Test
    fun `valid username and correctly repeated passwords return true`(){
        val result = RegistrationUtil.validateRegistrationInput(
            "Philip",
            "123456",
            "123456"
        )

        assertThat(result).isTrue()
    }

    @Test
    fun `username already exist returns false`(){
        val result = RegistrationUtil.validateRegistrationInput(
            "Carl",
            "123456",
            "123456"
        )

        assertThat(result).isFalse()
    }

    @Test
    fun `empty password returns false`(){
        val result = RegistrationUtil.validateRegistrationInput(
            "Philip",
            "",
            "123456"
        )

        assertThat(result).isFalse()
    }

    @Test
    fun `password repeated incorrectly return false`(){
        val result = RegistrationUtil.validateRegistrationInput(
            "Philip",
            "123456",
            "123457"
        )

        assertThat(result).isFalse()
    }

    @Test
    fun `password is less than 6 character returns false`(){
        val result = RegistrationUtil.validateRegistrationInput(
            "Philip",
            "123",
            "123"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `password contains less than 2 digit returns false`(){
        val result = RegistrationUtil.validateRegistrationInput(
            "Philip",
            "abcde1",
            "abcde1"
        )
        assertThat(result).isFalse()
    }
}