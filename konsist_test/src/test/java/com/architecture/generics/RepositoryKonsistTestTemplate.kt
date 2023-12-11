package com.architecture.generics

import com.architecture.common.Modules
import org.junit.jupiter.api.DynamicTest
import java.util.stream.Stream

interface RepositoryKonsistTestTemplate {

    fun `Repository classes should reside in repository package`(scopeModules: List<Modules>? = null): Stream<DynamicTest>
}