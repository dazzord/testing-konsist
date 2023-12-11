package com.architecture.common

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.container.KoScope

abstract class BaseTemplate {

    protected var customScope: KoScope = Konsist.scopeFromProduction()
    protected fun setCustomScopeForUseCasesLocationPackage(scopeModules: List<Modules>) {
        this.customScope = Konsist
            .scopeFromProduction()
            .slice{ it.resideInModule(scopeModules[0]) }
        scopeModules
            .drop(1)
            .forEach { module ->
                this.customScope = this.customScope + Konsist.scopeFromProduction().slice{ it.resideInModule(module) }
            }

    }
}