package com.architecture.generics.usecases

import com.architecture.common.BaseTemplate
import com.architecture.common.Modules
import com.architecture.extentions.appendShouldResideIn
import com.architecture.generics.UseCasesKonsistTestTemplate
import com.lemonappdev.konsist.api.ext.list.withNameEndingWith
import com.lemonappdev.konsist.api.verify.assertTrue
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.DynamicTest.dynamicTest
import java.util.stream.Stream

class UseCasesKonsistTestTemplateImpl: BaseTemplate(), UseCasesKonsistTestTemplate{

    private val useCasesPackageLocation = "..domain.usecase.."
    private val  USE_CASE = "UseCase"

    override fun `generic usecases location package`(scopeModules: List<Modules>?): Stream<DynamicTest>{
        scopeModules?.let {
            setCustomScopeForUseCasesLocationPackage(it)
        }

        return customScope
            .classes()
            .distinct()
            .withNameEndingWith(USE_CASE)
            .stream()
            .flatMap { useCase ->
                Stream.of(
                    dynamicTest(useCase.name.appendShouldResideIn(useCasesPackageLocation)) {
                        useCase.assertTrue(
                            testName = useCase
                                .name
                                .appendShouldResideIn(useCasesPackageLocation)
                        ) {
                            it.resideInPackage(useCasesPackageLocation)
                        }
                    },
                )
            }
    }
}