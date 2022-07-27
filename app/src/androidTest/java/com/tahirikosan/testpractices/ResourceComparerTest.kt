package com.tahirikosan.testpractices

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import com.tahirikosan.testpractices.exercise.ResourceComparer
import org.junit.After
import org.junit.Before
import org.junit.Test

class ResourceComparerTest{

    private lateinit var resourceComparer: ResourceComparer
    @Before
    fun setup(){
        resourceComparer = ResourceComparer()
    }

    @After
    fun teardown(){
        // After tests run.
    }

    @Test
    fun `stringResourceSameAsGivenString_returnsTrue`(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        val result = resourceComparer.isEqual(context, R.string.app_name, "TestPractices")

        assertThat(result).isTrue()
    }

    @Test
    fun `stringResourceDifferentFromGivenString_returnsFalse`(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        val result = resourceComparer.isEqual(context, R.string.app_name, "blabla")
        assertThat(result).isFalse()
    }
}