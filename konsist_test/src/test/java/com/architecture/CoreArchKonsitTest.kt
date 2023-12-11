package com.architecture

import com.architecture.generics.UseCasesKonsistTestTemplate
import com.architecture.generics.usecases.UseCasesKonsistTestTemplateImpl
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import java.util.stream.Stream

class CoreArchKonsistTest: UseCasesKonsistTestTemplate by UseCasesKonsistTestTemplateImpl(){

    private val moduleName = "core"


    @TestFactory
    fun `uses cases in module core should implement arch guidelines`(): Stream<DynamicTest> {
          return `generic usecases location package`(listOf(moduleName))
    }
}