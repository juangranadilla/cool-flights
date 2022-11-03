package com.juangranadilla.common

import org.junit.Rule
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

abstract class BaseDataTest {

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()
}