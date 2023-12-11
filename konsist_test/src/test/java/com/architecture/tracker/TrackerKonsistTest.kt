package com.architecture.tracker

import com.architecture.common.Modules
import com.architecture.generics.RepositoryKonsistTestTemplate
import com.architecture.generics.UseCasesKonsistTestTemplate
import com.architecture.generics.repository.RepositoryKonsistTestTemplateImpl
import com.architecture.generics.usecases.UseCasesKonsistTestTemplateImpl
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory
import java.util.stream.Stream

class TrackerKonsistTest: UseCasesKonsistTestTemplate by UseCasesKonsistTestTemplateImpl(),
RepositoryKonsistTestTemplate by RepositoryKonsistTestTemplateImpl(){

    private val trackerModules = listOf<Modules>(
        "tracker/tracker-domain",
        "core"
    )

    @TestFactory
    fun `uses cases in module core should implement arch guidelines`(): Stream<DynamicTest> {
        return `generic usecases location package`(trackerModules)
    }

    @TestFactory
    fun `Repository classes should reside in repository package`(): Stream<DynamicTest>{
        return `Repository classes should reside in repository package`(trackerModules)
    }

}