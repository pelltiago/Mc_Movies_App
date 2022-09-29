package com.example.imdbapp.core

import com.example.imdbapp.core.RetrofitHelper.getRetrofit
import junit.framework.Assert.assertNotNull
import org.junit.Test

class RetrofitTest {

    @Test
    fun getRetrofitShouldReturnARetrofitObject() {
        val retrofit = getRetrofit()
        assertNotNull(retrofit)
    }

}