package com.architecture.usecases

import com.architecture.extentions.appendShouldHaveTest
import com.architecture.extentions.appendShouldResideIn
import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.withNameEndingWith
import com.lemonappdev.konsist.api.verify.assertTrue
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.TestFactory
import java.util.stream.Stream

open class UseCaseKonsistTest{

    private val useCasesPackageLocation = "..domain.usecase.."
    private val  USE_CASE = "UseCase"

    @TestFactory
    fun `use case test`(): Stream<DynamicTest> = Konsist
        .scopeFromProject()
        .classes()
        .withNameEndingWith(USE_CASE)
        .stream()
        .flatMap { useCase ->
            Stream.of(
                dynamicTest(useCase.name.appendShouldHaveTest()) {
                    useCase.assertTrue(testName = useCase.name.appendShouldHaveTest()) {
                        it.hasTestClass()
                    }
                },
                dynamicTest(useCase.name.appendShouldResideIn("")) {
                    useCase.assertTrue(testName = useCase.name.appendShouldResideIn(useCasesPackageLocation)) {
                        it.resideInPackage(useCasesPackageLocation)
                    }
                },
            )
        }
}