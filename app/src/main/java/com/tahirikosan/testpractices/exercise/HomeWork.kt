package com.tahirikosan.testpractices.exercise

object HomeWork {

    /**
     * Returns the n-th fibonacci number
     * They are defined like this:
     * fib(0)=1
     * fib(1)=1
     * fib(n) = fib(n-1) + fib(n-2)
     */
    fun fib(n:Int):Long{
        return if(n < 2)
            1
        else
            fib(n-1) + fib(n-2)
    }

    /**
     * Checks if the braces are set correctly
     * e.g. "(a + b))" should return false
     */
    fun checkBraces(string: String): Boolean{
        return string.count { it == '(' } == string.count { it == ')' }
    }
}