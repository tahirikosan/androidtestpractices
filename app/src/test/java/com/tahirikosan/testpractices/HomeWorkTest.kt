package com.tahirikosan.testpractices


import com.google.common.truth.Truth.assertThat
import org.junit.Test

class HomeWorkTest{

    @Test
    fun `valid result returns true`(){
        // {1, 1, 2, 3, 5, 8, 13, 21, ... } fib value
        //  0, 1, 2, 3, 4, 5, 6, 7, ....  index
        val result = HomeWork.fib(6)

        assertThat(result).isEqualTo(13)
    }


    @Test
    fun `braces count of string is not equal returns false`(){
        val result = HomeWork.checkBraces("(a+b))")
        assertThat(result).isFalse()
    }
}