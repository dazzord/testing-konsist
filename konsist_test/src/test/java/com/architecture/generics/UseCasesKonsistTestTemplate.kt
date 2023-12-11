package com.architecture.generics

import com.architecture.common.Modules
import org.junit.jupiter.api.DynamicTest
import java.util.stream.Stream

interface UseCasesKonsistTestTemplate {

    fun `generic usecases location package`(scopeModules: List<Modules>? = null): Stream<DynamicTest>

}