package com.architecture.generics.repository

import com.architecture.common.BaseTemplate
import com.architecture.common.Modules
import com.architecture.extentions.appendShouldResideIn
import com.architecture.generics.RepositoryKonsistTestTemplate
import com.lemonappdev.konsist.api.ext.list.withNameEndingWith
import com.lemonappdev.konsist.api.verify.assertTrue
import org.junit.jupiter.api.DynamicTest
import java.util.stream.Stream

class RepositoryKonsistTestTemplateImpl:  BaseTemplate(), RepositoryKonsistTestTemplate {

    private val packageRepository = "..repository.."
    private val REPOSITORY = "Repository"

    override fun `Repository classes should reside in repository package`(scopeModules: List<Modules>?): Stream<DynamicTest> {
        scopeModules?.let {
            setCustomScopeForUseCasesLocationPackage(it)
        }

        return customScope
            .classes()
            .distinct()
            .withNameEndingWith(REPOSITORY)
            .stream()
            .flatMap { repository ->
                Stream.of(
                    DynamicTest.dynamicTest(
                        repository.name.appendShouldResideIn(
                            packageRepository
                        )
                    ) {
                        repository.assertTrue(
                            testName = repository
                                .name
                                .appendShouldResideIn(packageRepository)
                        ) {
                            it.resideInPackage(packageRepository)
                        }
                    },
                )
            }

    }
}